# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Apache Paimon is a lake format that enables building a realtime lakehouse architecture. It combines an LSM (Log-Structured Merge) tree for optimized streaming writes with a columnar lake format, supporting multiple compute engines (Flink, Spark, Hive).

## Build Commands

Maven-based project (requires Maven 3.6.3+). Default Java target is 1.8.

```bash
# Full build (skip tests)
mvn clean install -DskipTests -Pflink1,spark3

# Build specific module with dependencies
mvn -T 2C -B -ntp clean install -pl paimon-core -am -DskipTests

# Run all tests for a module
mvn test verify -pl paimon-core -Pflink1,spark3

# Run a single test class
mvn test -pl paimon-core -Dtest=MyTestClass

# Run a single test method
mvn test -pl paimon-core -Dtest=MyTestClass#myMethod

# Format code (must pass before committing)
mvn spotless:apply

# Check formatting without applying
mvn spotless:check
```

### Maven Profiles

- `flink1` (default) - Flink 1.16-1.20 support
- `flink2` - Flink 2.0-2.2 (requires JDK 11+)
- `spark3` (default) - Spark 3.2-3.5 (Scala 2.12)
- `spark4` - Spark 4.0 (Scala 2.13, requires JDK 17)
- `paimon-iceberg` - Iceberg compatibility (JDK 11+)
- `paimon-lucene` - Lucene indexing (JDK 11+)
- `paimon-faiss` - FAISS vector search

## Code Style

- **Java**: Google Java Format with AOSP style (4-space indent), enforced by Spotless
- **Scala**: Scalafmt (config in `.scalafmt.conf`), max 100 columns
- Import order: `org.apache.paimon`, `org.apache.paimon.shade`, other, `javax`, `java`, `scala`, static
- Shaded dependencies must use `org.apache.paimon.shade.*` packages (Guava, Jackson, Caffeine, Netty)
- File length limit: 4000 lines (enforced by Checkstyle)
- Test classes must be named `*Test.java` (not `*Tests.java`)
- No `TODO(username)` style comments - use plain `TODO`
- Code style guide: [Flink Java Code Style and Quality Guide](https://flink.apache.org/how-to-contribute/code-style-and-quality-java/)
- IDE: Mark `paimon-common/target/generated-sources/antlr4` as Sources Root

## Architecture

### Module Dependency Chain

```
paimon-api  (types, Table/Catalog interfaces, REST API definitions)
    ↓
paimon-common  (utilities, codegen, compression, file I/O, predicates)
    ↓
paimon-core  (FileStore, manifest, compaction, merge-tree, buckets)
    ↓
paimon-format  (Parquet, Avro, ORC file format implementations)
    ↓
paimon-bundle  (uber JAR aggregating api + common + core + format)
    ↓
paimon-flink / paimon-spark / paimon-hive  (engine integrations)
```

### Core Abstractions (paimon-core)

- **FileStore**: Central storage abstraction. `KeyValueFileStore` for update/delete tables (uses merge-tree), `AppendOnlyFileStore` for insert-only tables
- **Merge Tree**: LSM tree with sorted runs of key-value pairs; compaction merges runs for read efficiency
- **Snapshot/Manifest**: MVCC isolation via snapshots; manifests track file metadata with deterministic naming for atomicity
- **Partition/Bucket**: Data organized by partitions and buckets for pruning
- **Deletion Vectors**: Efficient row-level deletion tracking

### Engine Integration Pattern

Each engine (Flink, Spark) supports multiple versions via a common module plus version-specific shim modules:
- `paimon-flink/paimon-flink1-common/` + `paimon-flink-1.18/`, `paimon-flink-1.19/`, etc.
- `paimon-spark/paimon-spark3-common/` + `paimon-spark-3.3/`, `paimon-spark-3.4/`, etc.
- Scala code lives primarily in Spark modules; everything else is Java

### Type System (paimon-api)

`DataType` hierarchy in `org.apache.paimon.types` with `InternalRow` as the optimized internal row representation. Schema evolution supported via `SchemaChange`.

## Testing

- JUnit 5 (Jupiter) with AssertJ assertions
- Unit tests: `**/*Test.java` pattern (run by surefire default-test execution)
- Integration tests: other patterns (run by separate surefire execution with `reuseForks=false`)
- E2E tests in `paimon-e2e-tests/` module (uses TestContainers)
- Test utilities in `paimon-test-utils/`
- CI runs tests with randomized JVM timezones for robustness
- JVM test args: `-Xms256m -Xmx2048m -XX:+UseG1GC`
