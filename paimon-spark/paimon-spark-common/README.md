# Paimon Spark Common

Version-independent shared code for the Paimon Spark integration. This module contains the catalog implementations (`SparkCatalog`, `SparkGenericCatalog`), scan and read logic (`BaseScan`, `PaimonSplitScan`), write support (`PaimonBatchWrite`, `PaimonSparkWriter`), ANTLR4-based SQL extensions grammar, stored procedures, data type conversions between Paimon and Spark, and Catalyst analysis rules.

Key dependencies are `paimon-bundle`, Spark SQL / Core / Catalyst / Hive, and `antlr4-runtime`. The shaded JAR bundles `paimon-bundle` to produce a self-contained artifact that the major-version-common modules (`paimon-spark3-common`, `paimon-spark4-common`) build upon.
