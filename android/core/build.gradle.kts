import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.compose.compiler)
    id("maven-publish")
}

android {
    namespace = "com.masilotti.bridgecomponents"
    compileSdk = 35

    defaultConfig {
        minSdk = 28
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
          isMinifyEnabled = false
        }

        debug {
          isMinifyEnabled = false
        }
    }

    buildFeatures {
      compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
        freeCompilerArgs.add("-Xjvm-default=all")
    }
}

dependencies {
    api(libs.hotwire.core)

    implementation(libs.androidx.activity)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.hotwire.navigation.fragments)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.review.ktx)
    implementation(platform(libs.androidx.compose.bom))
}

group = "com.masilotti.bridgecomponents"
version = "unspecified"

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                artifactId = "core"
                pom {
                    name.set("Bridge Components")
                    description.set("A collection of bridge components for Hotwire Native apps (Kotlin components).")
                    url.set("https://github.com/joemasilotti/bridge-components")
                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }
                    scm { url.set("https://github.com/joemasilotti/bridge-components") }
                    developers {
                        developer {
                            id.set("joemasilotti")
                            name.set("Joe Masilotti")
                        }
                    }
                }
            }
        }
    }
}
