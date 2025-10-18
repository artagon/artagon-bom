# Changelog - artagon-bom

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2025-10-18

### Initial Release

#### Added
- Bill of Materials for centralized dependency version management
- Comprehensive dependency version properties covering:
  - Configuration frameworks (Weld, SmallRye Config)
  - Serialization (Jackson, Gson, Protocol Buffers)
  - Security (Nimbus JOSE+JWT, Bouncy Castle)
  - Reactive programming (RxJava, Vert.x, Reactor)
  - Cloud services (AWS SDK, GCP libraries)
  - Code generation (Truffle, Immutables, Jilt)
  - Testing frameworks (JUnit, AssertJ, Mockito, Truth)
  - Logging (SLF4J, Logback)
  - Utilities (Guava, Apache Commons, Caffeine)
- OSSRH deployment profile for Maven Central publishing
- GPG signing configuration
- Nexus staging plugin configuration
- Checksum generation for artifact verification
- Security checksum verification infrastructure

#### Configuration
- Java 25 compatibility
- UTF-8 encoding
- Reproducible builds with timestamp configuration
- Distribution management for Sonatype OSSRH

#### Documentation
- Comprehensive POM metadata (name, description, URL)
- Developer information
- License information (LGPL v3)
- SCM configuration
- Issue management (GitHub Issues)
- CI/CD management (GitHub Actions)

---

## Version History

- **1.0.0** - Initial release with comprehensive dependency management
