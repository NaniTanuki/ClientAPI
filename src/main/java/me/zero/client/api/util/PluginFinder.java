/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.api.util;

import com.google.common.collect.Sets;
import me.zero.client.api.ClientAPI;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import me.zero.client.api.event.defaults.PacketEvent;
import me.zero.client.api.event.defaults.TickEvent;
import me.zero.client.api.event.defaults.filters.PacketFilter;
import me.zero.client.api.exception.InvalidActionException;
import me.zero.client.api.util.interfaces.Helper;
import net.minecraft.network.play.client.CPacketTabComplete;
import net.minecraft.network.play.server.SPacketTabComplete;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Consumer;

import static me.zero.client.api.util.PluginFinder.PResponse.Result.*;

/**
 * Used to find possible plugins on servers
 *
 * @author Brady
 * @since 3/24/2017 12:00 PM
 */
public final class PluginFinder implements Helper {

    /**
     * Called in response to finding plugins
     */
    private Consumer<PResponse> callback;

    /**
     * Used to keep track of the timeout
     */
    private Timer packetTimer = new Timer();

    /**
     * Timeout in MS, how long the Plugin Finder will listen
     */
    private long timeout;

    /**
     * Finds the plugin list, the callback is called
     * when the request times out or the list has been
     * found.
     *
     * @param callback Plugin Response callback
     */
    public final void find(Consumer<PResponse> callback) {
        this.find(callback, 10000);
    }

    /**
     * Finds the plugin list, the callback is called
     * when the request times out or the list has been
     * found.
     *
     * @param callback Plugin Response callback
     * @param timeout Timeout in MS
     */
    public final void find(Consumer<PResponse> callback, long timeout) {
        packetTimer.reset();
        this.timeout = timeout;

        ClientAPI.EVENT_BUS.subscribe(this);
        this.callback = callback;
        mc.player.connection.sendPacket(new CPacketTabComplete("/", BlockPos.ORIGIN, false));
    }

    @EventHandler
    private final Listener<TickEvent> tickListener = new Listener<>(event -> {
        if (!packetTimer.delay(timeout))
            return;

        ClientAPI.EVENT_BUS.unsubscribe(this);
        callback.accept(new PResponse("Request timed out after " + timeout + "ms"));
    });

    @EventHandler
    private final Listener<PacketEvent.Receive> packetListener = new Listener<>(event -> {
        SPacketTabComplete packet = (SPacketTabComplete) event.getPacket();
        Set<String> plugins = Sets.newLinkedHashSet();

        Arrays.stream(packet.getMatches()).filter(s -> s.contains(":")).forEach(s -> {
            String plugin = s.split(":")[0].substring(1);
            if (!plugins.contains(plugin) && !plugin.equalsIgnoreCase("minecraft") && !plugin.equalsIgnoreCase("bukkit"))
                plugins.add(plugin);
        });

        callback.accept(new PResponse(plugins));
        callback = null;
        ClientAPI.EVENT_BUS.unsubscribe(this);
    }, new PacketFilter<>(SPacketTabComplete.class));

    public static class PResponse {

        /**
         * The list of plugins found
         */
        private final Set<String> plugins;

        /**
         * The last error
         */
        private final String error;

        /**
         * The result status, either SUCCESS or FAILURE
         */
        private final Result result;

        private PResponse(String error) {
            this.plugins = null;
            this.error = error;
            this.result = FAILURE;
        }

        private PResponse(Set<String> plugins) {
            this.plugins = plugins;
            this.error = null;
            this.result = SUCCESS;
        }

        /**
         * @return The plugins found, if found
         */
        public final Set<String> getPlugins() {
            if (result != SUCCESS)
                throw new InvalidActionException("Cannot get plugins that were retrieved unless response type is SUCCESS");

            return this.plugins;
        }

        /**
         * @return The last error, if there is one
         */
        public final String getError() {
            if (result != FAILURE)
                throw new InvalidActionException("Cannot get error that occured unless response type is FAILURE");

            return this.error;
        }

        /**
         * @return The outcome of the request, SUCCESS or FAILURE
         */
        public final Result getResult() {
            return this.result;
        }

        public enum Result {
            SUCCESS, FAILURE
        }
    }
}
