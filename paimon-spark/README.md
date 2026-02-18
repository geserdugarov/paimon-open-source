# Paimon Spark

Parent aggregator module for Apache Paimon's Spark integration. It uses a three-layer structure: `paimon-spark-common` (version-independent code), major-version-common modules (`paimon-spark3-common` / `paimon-spark4-common`), and version-specific shim modules (e.g. `paimon-spark-3.5`, `paimon-spark-4.0`).

The `spark3` Maven profile (default) activates Spark 3.2 through 3.5 with Scala 2.12. The `spark4` profile activates Spark 4.0 with Scala 2.13 and requires JDK 17. This parent POM configures the Scala compiler (`scala-maven-plugin`), the Scalatest runner, and shared dependencies such as `paimon-bundle`, `paimon-hive-common`, and the Scala standard library.
