# Paimon Spark3 Common

Spark 3.x-specific shared code (Scala 2.12). This module sits between `paimon-spark-common` and the individual Spark 3.x shim modules (3.2, 3.3, 3.4, 3.5). It provides `PaimonSpark3SqlExtensionsParser`, `Spark3ResolutionRules`, Spark 3 internal row and array adapters (`Spark3InternalRow`, `Spark3ArrayData`), and the `Spark3Shim` implementation.

The shaded JAR bundles both `paimon-bundle` and `paimon-spark-common`. This module is activated via the `spark3` Maven profile.
