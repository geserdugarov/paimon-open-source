/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.paimon.mergetree.compact.aggregate;

import org.apache.paimon.types.DataType;

/** first non-null value aggregate a field of a row. */
public class FieldFirstNonNullValueAgg extends FieldAggregator {

    private static final long serialVersionUID = 1L;

    private boolean initialized;

    public FieldFirstNonNullValueAgg(String name, DataType dataType) {
        super(name, dataType);
    }

    @Override
    public Object agg(Object accumulator, Object inputField) {
        if (!initialized && inputField != null) {
            initialized = true;
            return inputField;
        } else {
            return accumulator;
        }
    }

    @Override
    public void reset() {
        this.initialized = false;
    }
}
