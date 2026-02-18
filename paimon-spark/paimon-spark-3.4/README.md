# Paimon Spark 3.4

Version-specific shim module for Spark 3.4.3 (Scala 2.12). Depends on `paimon-spark3-common` and provides a smaller set of overrides: `SparkTable`, `MergePaimonScalarSubqueries` optimizer rule, and `OldCompatibleStrategy`.

Tests are inherited from `paimon-spark-ut` via a test-jar dependency. The shaded JAR bundles `paimon-spark3-common`. This module is activated via the `spark3` Maven profile.
