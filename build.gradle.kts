import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.asura"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("androidx.compose.material3:material3:1.0.1")
                implementation("androidx.compose.material3:material3-window-size-class:1.0.1")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            modules("java.instrument", "java.prefs", "java.sql", "java.naming", "jdk.unsupported","jdk.crypto.ec","jdk.localedata")

            packageName = "WordGames"
            packageVersion = "1.0.0"
            mainClass = "MainKt"

            buildTypes.release {
                proguard {
                    isEnabled.set(false)
                    // configurationFiles.from("compose.desktop.pro")
                }
            }
        }
    }
}
