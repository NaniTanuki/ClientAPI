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

package me.zero.client.load.mixin;

import me.zero.client.api.ClientAPI;
import me.zero.client.api.event.defaults.TextEvent;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(FontRenderer.class)
public abstract class MixinFontRenderer {

    @ModifyVariable(method = "renderStringAtPos", at = @At("HEAD"))
    public String renderStringAtPos(String text) {
        TextEvent event = new TextEvent(text);
        ClientAPI.EVENT_BUS.post(event);
        return event.getText();
    }

    @ModifyVariable(method = "getStringWidth", at = @At("HEAD"))
    public String getStringWidth(String text) {
        TextEvent event = new TextEvent(text);
        ClientAPI.EVENT_BUS.post(event);
        return event.getText();
    }
}
