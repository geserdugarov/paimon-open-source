# Paimon Spark 3.5

Version-specific shim module for Spark 3.5.8 (Scala 2.12). Depends on `paimon-spark3-common` and provides a single override: the `MergePaimonScalarSubqueries` optimizer rule.

Tests are inherited from `paimon-spark-ut` via a test-jar dependency. The shaded JAR bundles `paimon-spark3-common`. This module is activated via the `spark3` Maven profile.
