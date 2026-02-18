# Paimon Spark4 Common

Spark 4.x-specific shared code (Scala 2.13, requires JDK 17). This module sits between `paimon-spark-common` and the Spark 4.0 shim module. It provides `PaimonSpark4SqlExtensionsParser`, `Spark4ResolutionRules`, Spark 4 internal row and array adapters (`Spark4InternalRow`, `Spark4ArrayData`), and the `Spark4Shim` implementation.

Compared to `paimon-spark3-common`, this module adds an extra dependency on `spark-sql-api`. The shaded JAR bundles both `paimon-bundle` and `paimon-spark-common`. This module is activated via the `spark4` Maven profile.
