import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.sqldelight)
    id("kotlin-parcelize")
    id("kotlin-kapt")
    kotlin("plugin.serialization") version "1.9.23"
}

kotlin {
    // target declaration
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64() // ios simulator
    iosArm64() // iPhone5 up to
    iosSimulatorArm64() // simulators on m2 macs

    targets.filterIsInstance<KotlinNativeTarget>()
        .forEach {
            it.binaries.framework {
                baseName = "shared"
                isStatic = true

                // decompose export
                export(libs.decompose)
                export(libs.essenty.lifecycle.decompose)
            }
        }

    // source set declaration
    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(libs.sqldelight.runtime)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.jetbrains.kotlinx)
            api(libs.decompose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.sqldelight.android.driver)
        }
        iosMain.dependencies {
            implementation(libs.sqldelight.native.driver)
            api(libs.essenty.lifecycle.decompose)
        }
    }
}

sqldelight {
    databases {
        create("NoteDatabase") {
            packageName.set("com.linhcn.simplenoteapp")
        }
    }
}

android {
    namespace = "com.linhcn.simplenoteapp"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
