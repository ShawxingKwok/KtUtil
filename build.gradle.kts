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
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    js(IR) {
        browser()
        nodejs()
    }

    // val hostOs = System.getProperty("os.name")
    // when {
    //     hostOs == "Mac OS X" -> macosX64("native")
    //     hostOs == "Linux" -> linuxX64("native")
    //     hostOs.startsWith("Windows") -> mingwX64("native")
    //     else -> throw GradleException("Host OS $hostOs is not supported in Kotlin/Native.")
    // }
    // .binaries.executable { entryPoint = "main" }

    macosX64()
    macosArm64()

    iosX64()
    iosArm64()
    iosArm32()
    iosSimulatorArm64()

    watchosArm32()
    watchosArm64()
    watchosSimulatorArm64()
    watchosDeviceArm64()
    watchosX86()
    watchosX64()

    tvosArm64()
    tvosSimulatorArm64()
    tvosX64()

    mingwX64()
    mingwX86()

    linuxX64()
    linuxArm32Hfp()
    linuxMips32()

    sourceSets {
        configureEach {}

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
    val isSnapshot = false
    val version = "1.0.1"
    coordinates("io.github.shawxingkwok", "kt-util", if (isSnapshot) "$version-SNAPSHOT" else version)
    pom {
        val repo = "KtUtil"
        name.set(repo)
        description.set("Personal kt util")
        inceptionYear.set("2023")

        url.set("https://github.com/ShawxingKwok/$repo/")

        scm{
            connection.set("scm:git:git://github.com/ShawxingKwok/$repo.git")
            developerConnection.set("scm:git:ssh://git@github.com/ShawxingKwok/$repo.git")
        }
    }
}

rootProject.plugins.withType(YarnPlugin::class.java) {
    rootProject.the<YarnRootExtension>().yarnLockMismatchReport =
        YarnLockMismatchReport.WARNING // NONE | FAIL
    rootProject.the<YarnRootExtension>().reportNewYarnLock = false // true
    rootProject.the<YarnRootExtension>().yarnLockAutoReplace = false // true
}