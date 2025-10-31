# Artagon BOM

[![Maven Central](https://img.shields.io/maven-central/v/com.artagon/artagon-bom?label=Maven%20Central)](https://central.sonatype.com/artifact/com.artagon/artagon-bom)
[![License: AGPL-3.0](https://img.shields.io/badge/License-AGPL%203.0-blue.svg)](https://www.gnu.org/licenses/agpl-3.0)
[![SBOM](https://img.shields.io/badge/SBOM-CycloneDX%201.6-green.svg)](https://github.com/artagon/artagon-bom/releases/latest)
[![Sigstore](https://img.shields.io/badge/Sigstore-Signed-orange.svg)](https://github.com/artagon/artagon-bom/releases/latest)

A comprehensive Bill of Materials (BOM) for JVM projects, providing centralized dependency version management across the Artagon stack with enterprise-grade security features.

## Overview

The Artagon BOM provides:

- **Centralized Dependency Management**: Consistent versions across all Artagon projects
- **Curated Dependencies**: 14 upstream BOMs and 50+ managed dependencies
- **Security First**: SBOM generation, Sigstore signing, and dependency verification
- **Modern Tooling**: Built with Gradle 9.2.0 using the java-platform plugin
- **100% Maven Compatible**: Works seamlessly with both Gradle and Maven projects

## Quick Start

### Maven Projects

Add the BOM to your `dependencyManagement` section:

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>com.artagon</groupId>
      <artifactId>artagon-bom</artifactId>
      <version>1.0.9</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>
```

Then declare dependencies without versions:

```xml
<dependencies>
  <dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <!-- Version managed by BOM -->
  </dependency>
</dependencies>
```

### Gradle Projects

Add the BOM as a platform dependency:

```kotlin
dependencies {
    // Import the BOM
    implementation(platform("com.artagon:artagon-bom:1.0.9"))

    // Use dependencies without versions
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.google.guava:guava")
}
```

Or with Groovy DSL:

```groovy
dependencies {
    implementation platform('com.artagon:artagon-bom:1.0.9')
    implementation 'com.fasterxml.jackson.core:jackson-databind'
}
```

## Features

### 🔒 Supply Chain Security

#### SBOM Generation
Every release includes Software Bill of Materials (SBOM) in multiple formats:
- **CycloneDX 1.6** (JSON and XML)
- Attached to [GitHub releases](https://github.com/artagon/artagon-bom/releases)
- Documents all managed dependencies and constraints

#### Sigstore Keyless Signing
All SBOMs are signed using [Sigstore](https://www.sigstore.dev/):
- Keyless signing with ephemeral keys
- Transparency log entries
- Certificate and signature included with releases
- Files: `bom.json.sig`, `bom.json.pem`

#### Dependency Verification
- Gradle dependency verification with SHA-256 checksums
- Gradle wrapper integrity verification
- Reproducible builds

### 🏗️ Build System

**Gradle 9.2.0** with:
- `java-platform` plugin for first-class BOM support
- Kotlin DSL for type-safe configuration
- Gradle wrapper with integrity verification
- Configuration cache support

### 📦 Managed Dependencies

The BOM manages versions for:

#### Upstream BOMs (14)
- Configuration: Weld, SmallRye Config
- Serialization: Jackson, Guava
- Testing: JUnit, Mockito, Testcontainers
- Logging: SLF4J, Logback
- Observability: OpenTelemetry, Metrics
- Cloud: AWS SDK
- Code Quality: SpotBugs
- JSON Processing: JSurfer

#### Direct Constraints (50+)
- Annotations: JSpecify, JetBrains, JSR-305
- Collections: Eclipse Collections
- Testing: AssertJ, Truth, Compile Testing
- Benchmarking: JMH
- Reactive: RxJava, Vert.x
- Security: Nimbus JOSE+JWT, OAuth 2.0
- Configuration: Archaius, MicroProfile Config
- Date/Time: Time4J
- And many more...

See the [dependency list](build.gradle.kts) for complete details.

## Security

### Automated Security Scanning

Every release is automatically scanned for vulnerabilities:
- **OWASP Dependency-Check**: CVE identification with CVSS scoring
- **Sonatype OSS Index**: Vulnerability database cross-reference
- **Trivy**: Filesystem security scanning
- **SpotBugs**: Bytecode security analysis

Releases **automatically fail** if HIGH or CRITICAL vulnerabilities are detected.

### Security Artifacts

Each release includes:
- `bom.json` - CycloneDX SBOM (JSON format)
- `bom.xml` - CycloneDX SBOM (XML format)
- `bom.json.sig` - Sigstore signature
- `bom.json.pem` - Sigstore certificate

### Verifying Signatures

Verify SBOM signatures using Cosign:

```bash
# Install cosign
brew install cosign  # or appropriate package manager

# Verify the SBOM signature
cosign verify-blob \
  --certificate bom.json.pem \
  --signature bom.json.sig \
  --certificate-identity-regexp="https://github.com/artagon/artagon-bom" \
  --certificate-oidc-issuer="https://token.actions.githubusercontent.com" \
  bom.json
```

## Building from Source

### Prerequisites
- JDK 25 (Temurin recommended)
- Gradle 9.2.0 (included via wrapper)

### Build Commands

```bash
# Clean build with SBOM generation
./gradlew clean build

# Run all checks
./gradlew check

# Generate SBOM only
./gradlew cyclonedxBom

# Publish to Maven Local (for testing)
./gradlew publishToMavenLocal
```

### Publishing to Maven Central

This project uses the [vanniktech gradle-maven-publish-plugin](https://vanniktech.github.io/gradle-maven-publish-plugin/) for publishing to Maven Central Portal.

**Environment Variables Required:**
```bash
ORG_GRADLE_PROJECT_mavenCentralUsername=<your-token-username>
ORG_GRADLE_PROJECT_mavenCentralPassword=<your-token-password>
ORG_GRADLE_PROJECT_signingInMemoryKey=<gpg-private-key>
ORG_GRADLE_PROJECT_signingInMemoryKeyPassword=<gpg-passphrase>
```

**Publish Snapshot:**
```bash
./gradlew publishToMavenCentral
```

**Publish Release (with automatic publishing):**
```bash
./gradlew publishAndReleaseToMavenCentral
```

## Migration Notes

### v1.0.9: Maven to Gradle Migration

Version 1.0.9 represents a complete migration from Maven to Gradle:

**Build System Changes:**
- Migrated to Gradle 9.2.0 with `java-platform` plugin
- Kotlin DSL for type-safe build configuration
- Gradle wrapper with SHA-256 integrity verification
- Reproducible builds with deterministic timestamps

**New Features:**
- CycloneDX 1.6 SBOM generation (JSON + XML)
- Sigstore keyless signing for SBOMs
- Automatic publishing to Maven Central Portal
- Enhanced dependency verification

**Compatibility:**
- 100% compatible with Maven consumers
- All dependency constraints preserved
- No breaking changes to published artifacts

**For Consumers:**
No changes required! The BOM continues to work exactly the same way in your Maven or Gradle projects. Only the `groupId` changed from `org.artagon` to `com.artagon`.

## CI/CD

### Workflows

**CI (`ci.yml`):**
- Runs on all PRs and pushes
- Validates Gradle wrapper
- Builds project and generates SBOM
- Runs all checks

**Snapshot Deployment (`snapshot-deploy.yml`):**
- Triggers on push to `main` (if version is SNAPSHOT)
- Builds and generates SBOM
- Publishes to Maven Central snapshots

**Release (`release.yml`):**
- Triggers on version tags (`v*`, `bom-v*`)
- Builds and generates SBOM with signing
- Publishes to Maven Central with automatic release
- Signs SBOM with Sigstore
- Creates GitHub release with artifacts

## Documentation

### Common Documentation
General-purpose documentation is maintained in artagon-common:
- **[Security Scripts Guide](.common/artagon-common/docs/SECURITY-SCRIPTS.md)**
- **[Release Guide](.common/artagon-common/docs/RELEASE-GUIDE.md)**
- **[Deployment Guide](.common/artagon-common/docs/DEPLOYMENT.md)**
- **[Complete Documentation Index](.common/artagon-common/docs/README.md)**

### Project-Specific Guides
- **[CHANGELOG.md](CHANGELOG.md)** - Version history
- **[build.gradle.kts](build.gradle.kts)** - Build configuration
- **[Contributing Guide](CONTRIBUTING.md)** - How to contribute

## Licensing

This project uses a dual licensing model:

- **GNU Affero General Public License v3.0 (AGPL-3.0)** for open source use
- **Commercial License** for proprietary use with expanded rights and support

See [licenses/LICENSING.md](licenses/LICENSING.md) for details.

## Support

- **Issues**: [GitHub Issues](https://github.com/artagon/artagon-bom/issues)
- **Security**: See [SECURITY.md](SECURITY.md) for reporting vulnerabilities
- **Commercial Support**: sales@artagon.com
- **Documentation**: [Full documentation index](.common/artagon-common/docs/README.md)

## Related Projects

- [artagon-workflows](https://github.com/artagon/artagon-workflows) - Reusable GitHub Actions workflows
- [artagon-common](https://github.com/artagon/artagon-common) - Shared documentation and utilities

---

**Latest Release**: [v1.0.9](https://github.com/artagon/artagon-bom/releases/tag/v1.0.9)
**Maven Central**: [com.artagon:artagon-bom](https://central.sonatype.com/artifact/com.artagon/artagon-bom)
