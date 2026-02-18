# Paimon Spark 3.3

Version-specific shim module for Spark 3.3.4 (Scala 2.12). Depends on `paimon-spark3-common` and carries the most overrides among the 3.x shims: `PaimonScan`, `SparkTable`, `Compatibility`, merge-into analysis, `MergePaimonScalarSubqueries` optimizer rule, `OldCompatibleStrategy`, runtime filtering, `SQLConfUtils`, `TypeUtils`, and stubs for `TableCatalogCapability` and `SupportsReportOrdering`.

Tests are inherited from `paimon-spark-ut` via a test-jar dependency. The shaded JAR bundles `paimon-spark3-common`. This module is activated via the `spark3` Maven profile.
