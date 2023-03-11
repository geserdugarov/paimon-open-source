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

package org.apache.flink.table.store.file.mergetree.compact;

import org.apache.flink.table.store.data.InternalRow;
import org.apache.flink.table.store.file.KeyValue;
import org.apache.flink.table.store.file.io.DataFileMeta;
import org.apache.flink.table.store.file.io.KeyValueFileReaderFactory;
import org.apache.flink.table.store.file.io.KeyValueFileWriterFactory;
import org.apache.flink.table.store.file.mergetree.SortedRun;
import org.apache.flink.table.store.utils.Preconditions;

import java.util.Comparator;
import java.util.List;

/** A {@link MergeTreeCompactRewriter} which produces changelog files for each full compaction. */
public class FullChangelogMergeTreeCompactRewriter extends ChangelogMergeTreeRewriter {

    private final int maxLevel;

    public FullChangelogMergeTreeCompactRewriter(
            int maxLevel,
            KeyValueFileReaderFactory readerFactory,
            KeyValueFileWriterFactory writerFactory,
            Comparator<InternalRow> keyComparator,
            MergeFunctionFactory<KeyValue> mfFactory) {
        super(readerFactory, writerFactory, keyComparator, mfFactory);
        this.maxLevel = maxLevel;
    }

    @Override
    protected boolean rewriteChangelog(
            int outputLevel, boolean dropDelete, List<List<SortedRun>> sections) {
        boolean changelog = outputLevel == maxLevel;
        if (changelog) {
            Preconditions.checkArgument(
                    dropDelete,
                    "Delete records should be dropped from result of full compaction. This is unexpected.");
        }
        return changelog;
    }

    @Override
    protected boolean upgradeChangelog(int outputLevel, DataFileMeta file) {
        return outputLevel == maxLevel;
    }

    @Override
    protected MergeFunctionWrapper<ChangelogResult> createMergeWrapper(int outputLevel) {
        return new FullChangelogMergeFunctionWrapper(mfFactory.create(), maxLevel);
    }
}
