# Paimon Spark 4.0

Version-specific shim module for Spark 4.0.2 (Scala 2.13, requires JDK 17). Depends on both `paimon-spark4-common` and `paimon-spark-common`, and provides a single override: the `MergePaimonScalarSubqueries` optimizer rule. It also includes `paimon-lucene` as a test dependency.

Tests are inherited from `paimon-spark-ut` via a test-jar dependency. The shaded JAR bundles `paimon-spark4-common`. This module is activated via the `spark4` Maven profile.
