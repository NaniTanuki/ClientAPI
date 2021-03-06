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

package me.zero.client.api.gui.tab;

import me.zero.client.api.gui.IRenderer;
import me.zero.client.api.util.interfaces.Loadable;

/**
 * Base for Tab Gui Handlers
 *
 * @see me.zero.client.api.gui.tab.ITabGuiElement
 *
 * @author Brady
 * @since 1/20/2017 12:00 PM
 */
public interface ITabGui extends Loadable, IRenderer {

    /**
     * @return The main menu of this Tab GUI
     */
    ITabGuiMenu getMenu();
}
