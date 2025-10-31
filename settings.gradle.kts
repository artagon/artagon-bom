/*
 * Artagon BOM - Settings Configuration
 * Secure Gradle configuration with repository allow-listing
 */

rootProject.name = "artagon-bom"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }

    plugins {
        id("org.cyclonedx.bom") version "1.10.0"
        id("com.vanniktech.maven.publish") version "0.30.0"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
}

// Enable Gradle feature previews (unrelated to dependency verification)
// See https://docs.gradle.org/current/userguide/feature_lifecycle.html
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// Dependency verification is enabled via verification-metadata.xml
// Generate with: ./gradlew --write-verification-metadata sha256 help
