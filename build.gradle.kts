/*
 * Artagon BOM - Bill of Materials
 * Gradle platform for managing dependency versions across the Artagon stack
 */

plugins {
    `java-platform`
    `maven-publish`
    signing
    id("org.cyclonedx.bom") version "1.10.0"
}

group = "com.artagon"
version = "1.0.9"

description = "Bill of Materials for managing dependency versions across the Artagon stack."

// Platform allows dependencies
javaPlatform {
    allowDependencies()
}

// Dependency locking
dependencyLocking {
    lockAllConfigurations()
}

dependencies {
    // Import upstream BOMs
    api(platform("org.jboss.weld:weld-core-bom:6.0.3.Final"))
    api(platform("io.smallrye.config:smallrye-config-bom:3.13.4"))
    api(platform("com.fasterxml.jackson:jackson-bom:2.19.2"))
    api(platform("com.google.guava:guava-bom:33.1.0-jre"))
    api(platform("org.junit:junit-bom:5.11.3"))
    api(platform("org.mockito:mockito-bom:5.12.0"))
    api(platform("org.testcontainers:testcontainers-bom:1.20.3"))
    api(platform("org.slf4j:slf4j-bom:2.0.17"))
    api(platform("ch.qos.logback:logback-parent:1.5.19"))
    api(platform("io.opentelemetry:opentelemetry-bom:1.44.1"))
    api(platform("io.dropwizard.metrics:metrics-bom:4.2.28"))
    api(platform("software.amazon.awssdk:bom:2.32.14"))
    api(platform("com.github.spotbugs:spotbugs:4.9.3"))
    api(platform("com.github.jsurfer:jsurfer:1.6.5"))

    // Define version constraints
    constraints {
        // Annotations
        api("org.jspecify:jspecify:1.0.0")
        api("org.jetbrains:annotations:26.0.2")
        api("com.google.code.findbugs:jsr305:3.0.2")
        api("javax.annotation:javax.annotation-api:1.3.2")

        // Collections
        api("org.eclipse.collections:eclipse-collections:11.1.0")

        // Testing
        api("org.assertj:assertj-core:3.27.3")
        api("com.google.truth:truth:1.4.4")
        api("com.google.truth.extensions:truth-java8-extension:1.4.4")
        api("com.google.testing.compile:compile-testing:0.21.0")

        // Benchmarking
        api("org.openjdk.jmh:jmh-core:1.37")
        api("org.openjdk.jmh:jmh-generator-annprocess:1.37")

        // Reactive
        api("io.reactivex.rxjava3:rxjava:3.1.10")
        api("io.vertx:vertx-core:4.5.11")

        // Security
        api("com.nimbusds:nimbus-jose-jwt:9.47")
        api("com.nimbusds:oauth2-oidc-sdk:11.20.1")

        // Configuration
        api("com.netflix.archaius:archaius2-api:2.8.2")
        api("com.netflix.archaius:archaius2-core:2.8.2")
        api("com.netflix.archaius:archaius2-typesafe:2.8.2")
        api("org.eclipse.microprofile.config:microprofile-config-api:3.1")

        // Date/Time
        api("net.time4j:time4j-base:5.9.4")

        // Networking
        api("com.github.seancfoley:ipaddress:5.5.0")

        // Serialization
        api("com.google.code.gson:gson:2.11.0")
        api("com.google.protobuf:protobuf-java:4.28.3")
        api("com.google.protobuf:protobuf-java-util:4.28.3")
        api("jakarta.xml.bind:jakarta.xml.bind-api:4.0.2")
        api("com.jayway.jsonpath:json-path:2.9.0")

        // Code Generation
        api("com.google.auto.service:auto-service:1.1.1")
        api("cc.jilt:jilt:1.8.2")
        api("com.squareup:javapoet:1.13.0")

        // Truffle
        api("org.graalvm.truffle:truffle-api:24.2.2")
        api("org.graalvm.truffle:truffle-runtime:24.2.2")
        api("org.graalvm.truffle:truffle-strings:24.2.2")
        api("org.graalvm.truffle:truffle-dsl-processor:24.2.2")

        // Utilities
        api("de.slub-dresden:urnlib:[2.0,2.1)")
        api("com.github.parsnip:parsnip:1.0.0")
    }
}

// CycloneDX SBOM configuration
// For BOM projects, we document the managed dependency constraints
tasks.cyclonedxBom {
    schemaVersion.set("1.6")
    projectType.set("library")
    includeBomSerialNumber.set(true)
    includeLicenseText.set(true)
    outputFormat.set("all")
    outputName.set("bom")
    destination.set(file("build/reports"))

    // Include metadata about the BOM itself
    includeConfigs.set(listOf("runtimeClasspath"))
}

// Publishing configuration
publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["javaPlatform"])

            pom {
                name.set("Artagon OSS BOM")
                description.set("Bill of Materials for managing dependency versions across the Artagon stack.")
                url.set("https://github.com/artagon")
                inceptionYear.set("2025")

                licenses {
                    license {
                        name.set("GNU Affero General Public License v3.0")
                        url.set("https://www.gnu.org/licenses/agpl-3.0.txt")
                        distribution.set("repo")
                        comments.set("Open source option - see LICENSE file for dual licensing details")
                    }
                }

                developers {
                    developer {
                        id.set("giedrius")
                        name.set("Giedrius Trumpickas")
                        email.set("trumpyla at artagon dot com")
                        roles.set(listOf("Founder"))
                        timezone.set("+5")
                    }
                }

                organization {
                    name.set("Artagon")
                    url.set("https://github.com/artagon")
                }

                scm {
                    connection.set("scm:git:https://github.com/artagon/artagon-bom.git")
                    developerConnection.set("scm:git:ssh://git@github.com/artagon/artagon-bom.git")
                    url.set("https://github.com/artagon/artagon-bom")
                }

                issueManagement {
                    system.set("GitHub Issues")
                    url.set("https://github.com/artagon/artagon-bom/issues")
                }
            }
        }
    }

    repositories {
        maven {
            name = "OSSRH"
            val releasesRepoUrl = uri("https://central.sonatype.com")
            val snapshotsRepoUrl = uri("https://central.sonatype.com/repository/maven-snapshots/")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl

            credentials {
                username = providers.environmentVariable("OSSRH_USERNAME").orNull
                password = providers.environmentVariable("OSSRH_PASSWORD").orNull
            }
        }
    }
}

// Signing configuration
signing {
    val signingKey = providers.environmentVariable("GPG_PRIVATE_KEY").orNull
    val signingPassword = providers.environmentVariable("GPG_PASSPHRASE").orNull

    // Only sign if keys are provided
    isRequired = signingKey != null && signingPassword != null

    if (signingKey != null && signingPassword != null) {
        useInMemoryPgpKeys(signingKey, signingPassword)
    }

    sign(publishing.publications["maven"])
}

// Reproducible builds
tasks.withType<AbstractArchiveTask>().configureEach {
    isPreserveFileTimestamps = false
    isReproducibleFileOrder = true
}

// Include SBOM generation in build process
tasks.named("build") {
    dependsOn("cyclonedxBom")
}
