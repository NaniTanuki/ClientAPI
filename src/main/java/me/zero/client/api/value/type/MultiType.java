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

package me.zero.client.api.value.type;

import me.zero.client.api.util.ClientUtils;
import me.zero.client.api.value.Value;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;

/**
 * A value that can have a specific set of values
 *
 * @author Brady
 * @since 2/24/2017 12:00 PM
 */
public final class MultiType extends Value<String> {

    /**
     * Different values
     */
    private final String[] values;

    public MultiType(String name, String id, String description, Object object, Field field, String[] values) {
        super(name, id, description, object, field);
        this.values = values;
        this.setValue(values[0]);
    }

    @Override
    public final void setValue(String value) {
        super.setValue(ClientUtils.objectFrom(value, values));
    }

    /**
     * Sets value to the next one in the set
     */
    public final void next() {
        int index = ArrayUtils.indexOf(values, getValue());
        if (++index >= values.length)
            index = 0;
        this.setValue(values[index]);
    }

    /**
     * Sets value to the last one in the set
     */
    public final void last() {
        int index = ArrayUtils.indexOf(values, getValue());
        if (--index < 0)
            index = values.length - 1;
        this.setValue(values[index]);
    }

    /**
     * @return All possible values for this MultiType
     */
    public final String[] getValues() {
        return this.values;
    }
}
