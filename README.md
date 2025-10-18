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

The BOM enforces dependency integrity through multiple security baselines:

- `security/com.artagon-artagon-bom-dependency-checksums.csv`: SHA-256 checksums for all compile-scope dependencies (including transitives)
- `security/pgp-trusted-keys.list`: Trusted PGP fingerprints for dependency verification
- `security/pom.xml.sha256`: SHA-256 checksum for the published BOM
- `security/pom.xml.sha512`: SHA-512 checksum for the published BOM

Regenerate these files only after intentional changes to the BOM and
before staging a release.

### Updating Dependency Security Baselines

Use the helper script to regenerate dependency checksums and PGP keys:

```bash
# Update baselines
scripts/update-dependency-security.sh --update

# Verify baselines are current
scripts/update-dependency-security.sh --verify

# Show all available options
scripts/update-dependency-security.sh --help
```

## Documentation

### Common Documentation

General-purpose documentation is maintained in artagon-common:

- **[Security Scripts Guide](.common/artagon-common/docs/SECURITY-SCRIPTS.md)** - Using security verification scripts
- **[Release Guide](.common/artagon-common/docs/RELEASE-GUIDE.md)** - How to create releases
- **[Deployment Guide](.common/artagon-common/docs/DEPLOYMENT.md)** - Deploying to Maven Central
- **[GitHub Packages Guide](.common/artagon-common/docs/GITHUB-PACKAGES.md)** - Using GitHub Packages
- **[Licensing Implementation](.common/artagon-common/docs/licensing/IMPLEMENTATION-GUIDE.md)** - Dual licensing setup
- **[Complete Documentation Index](.common/artagon-common/docs/README.md)** - All available documentation

### Project-Specific Guides

- **[CHANGELOG.md](CHANGELOG.md)** - Version history and changes
- **[security/README.md](security/README.md)** - Security baseline file documentation

## Licensing

This project uses a dual licensing model:

- **GNU Affero General Public License v3.0 (AGPL-3.0)** for open source
  use. See [`licenses/LICENSE-AGPL.txt`](licenses/LICENSE-AGPL.txt) for the full text.
- **Commercial License** for proprietary use, available from Artagon LLC
  with expanded rights, warranties, and support. Review
  [`licenses/LICENSE-COMMERCIAL.txt`](licenses/LICENSE-COMMERCIAL.txt) or
  contact `sales@artagon.com`.

Need help choosing? Read [`licenses/LICENSING.md`](licenses/LICENSING.md) for
a decision guide. Commercial pricing is available at
https://www.artagon.com/pricing.
