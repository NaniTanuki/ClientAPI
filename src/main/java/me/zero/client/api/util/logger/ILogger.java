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

package me.zero.client.api.util.logger;

/**
 * Base for Loggers
 *
 * @see me.zero.client.api.util.logger.Logger
 *
 * @author Brady
 * @since 1/21/2017 12:00 PM
 */
public interface ILogger {

    /**
     * Logs a message with a level
     *
     * @param level The level
     * @param message The message
     */
    void log(Level level, String message);

    /**
     * Logs a formatted message with a level
     *
     * @param level The level
     * @param message The message
     * @param args The format arguments
     */
    void logf(Level level, String message, Object... args);
}
