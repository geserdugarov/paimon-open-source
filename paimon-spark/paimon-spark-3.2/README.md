# Paimon Spark 3.2

Version-specific shim module for Spark 3.2.4 (Scala 2.12). Depends on `paimon-spark3-common` and provides overrides for APIs not yet available or different in Spark 3.2, including `PaimonBaseScanBuilder`, `PaimonScan`, `SparkTable`, `Compatibility`, `OldCompatibleStrategy`, runtime filtering, and a `TableCatalogCapability` stub.

Tests are inherited from `paimon-spark-ut` via a test-jar dependency. The shaded JAR bundles `paimon-spark3-common`. This module is activated via the `spark3` Maven profile.
