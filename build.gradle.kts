import org.jetbrains.dokka.DokkaConfiguration.Visibility
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnLockMismatchReport
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
    id ("org.jetbrains.kotlin.multiplatform") version "1.8.0"
    id("org.jetbrains.dokka") version "1.7.20"
    id ("com.vanniktech.maven.publish") version "0.25.2"
}

repositories {
    mavenCentral()
}

kotlin {
    explicitApi()

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }

    js(IR) {
        browser()
        nodejs()
    }

    mingwX64()
    macosArm64()
    macosX64()
    ios()
    linuxX64()
    iosSimulatorArm64()

    sourceSets {
        configureEach {
        }

        getByName("commonMain") {
            dependencies {

            }
        }

        getByName("commonTest") {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        getByName("jvmTest") {
            dependencies {
                implementation(kotlin("test-junit5"))
                implementation("org.junit.jupiter:junit-jupiter-api:5.6.0")

                runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
            }
        }

        getByName("jsTest") {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

// dokka
tasks.withType<DokkaTask>().configureEach {
    suppressInheritedMembers.set(true)

    outputDirectory.set(file("/Users/william/IdeaProjects/library/ITWorks/content/docs/multiplatform/kt-util/html"))

    dokkaSourceSets.configureEach {
        documentedVisibilities.set(
            setOf(
                Visibility.PUBLIC,
                Visibility.PROTECTED,
            )
        )

        perPackageOption {
            matchingRegex.set(".*internal.*")
            suppress.set(true)
        }
    }
}

// publish
mavenPublishing {
    coordinates("io.github.shawxingkwok", "kt-util", "1.0.0-SNAPSHOT")
    pom {
        val repoName = "KtUtil"

        name.set("Personal kt util")
        description.set("")
        inceptionYear.set("2023")
        url.set("https://github.com/ShawxingKwok/$repoName/")

        scm{
            connection.set("scm:git:git://github.com/ShawxingKwok/$repoName.git")
            developerConnection.set("scm:git:ssh://git@github.com/ShawxingKwok/$repoName.git")
        }
    }
}

rootProject.plugins.withType(YarnPlugin::class.java) {
    rootProject.the<YarnRootExtension>().yarnLockMismatchReport =
        YarnLockMismatchReport.WARNING // NONE | FAIL
    rootProject.the<YarnRootExtension>().reportNewYarnLock = false // true
    rootProject.the<YarnRootExtension>().yarnLockAutoReplace = false // true
}