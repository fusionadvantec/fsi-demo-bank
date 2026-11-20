# fsi-demo-bank

Fictional Thai bank demo repository for training and security tooling demos.
**All names, accounts, IDs, and credentials are fake.**

## Layout

| Path | Description |
|------|-------------|
| `core-banking/` | Java 8-style core module (`TransferProcessor`, `AccountService`, `LegacyDao`, `InterestCalculator`, `CustomerLookupDao`) |
| `cobol/INTCALC.cbl` | COBOL program with tiered deposit interest rules |
| `services/transfer-api/` | Spring Boot 3 / Java 17 Maven service with unit tests |
| `data/sample/` | Fictional CSV sample data |

## Security demo notes

`CustomerLookupDao` intentionally contains insecure patterns for scanners/demos only:

- string-concatenated SQL
- fake hard-coded password
- log line with a fake citizen ID

Do not copy these patterns into real systems.

## Build / test (transfer-api)

```bash
cd services/transfer-api
mvn test
```
