# Artagon BOM

This module defines the Artagon Bill of Materials (BOM) for JVM projects.
Importing the BOM centralises dependency versions across Artagon services
and libraries.

## Usage

Add the BOM to your Maven project via `dependencyManagement`:

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>org.artagon</groupId>
      <artifactId>artagon-bom</artifactId>
      <version>1.0.0-SNAPSHOT</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>
```

Consume dependencies without version numbers once the BOM is imported.
Keeping services aligned with the BOM ensures security patches and
transitive dependency updates are applied consistently.

## Security Baseline

- `security/pom.xml.sha256`: SHA-256 checksum for the published BOM.
- `security/pom.xml.sha512`: SHA-512 checksum for the published BOM.

Regenerate these files only after intentional changes to the BOM and
before staging a release.

## Licensing

This project uses a dual licensing model:

- **GNU Affero General Public License v3.0 (AGPL-3.0)** for open source
  use. See `LICENSE-AGPL.txt` for the full text.
- **Commercial License** for proprietary use, available from Artagon LLC
  with expanded rights, warranties, and support. Review `LICENSE-
  COMMERCIAL.txt` or contact `sales@artagon.com`.

Need help choosing? Read `LICENSING.md` for a decision guide. Commercial
pricing is available at https://www.artagon.com/pricing.
