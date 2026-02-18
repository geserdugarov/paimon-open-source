# Paimon Spark UT

Shared test base classes for the Paimon Spark integration. This module has no main sources — it only contains test code and publishes a `test-jar` consumed by all version-specific shim modules (`paimon-spark-3.2` through `paimon-spark-4.0`).

Key base classes include `PaimonSparkTestBase`, `PaimonHiveTestBase`, and numerous test suites covering DDL, reads, writes, merge-into, procedures, schema evolution, and more. Writing tests here allows them to run against every supported Spark version without duplication.
