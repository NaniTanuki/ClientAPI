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

package me.zero.client.load.mixin.wrapper;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 2/24/2017 12:00 PM
 */
@Mixin(PlayerControllerMP.class)
public interface IPlayerControllerMP {

    /**
     * @return PlayerControllerMP#isHittingBlock
     */
    @Accessor boolean getIsHittingBlock();

    /**
     * @return PlayerControllerMP#curBlockDamageMP
     */
    @Accessor float getCurBlockDamageMP();

    /**
     * Sets PlayerControllerMP#curBlockDamageMP
     *
     * @param damage New damage value
     */
    @Accessor void setCurBlockDamageMP(float damage);
}
