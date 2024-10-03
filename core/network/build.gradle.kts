import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp.plugin)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.android.junit5)
    alias(libs.plugins.kover.plugin)
    id("kotlinx-serialization")
}

android {
    namespace =
        libs.plugins.networkNameSpace
            .get()
            .toString()
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    defaultConfig {
        val properties = Properties()
        file(rootProject.file("token.properties")).inputStream().use { properties.load(it) }
        val token: String = properties.getProperty("TOKEN") ?: ""
        buildConfigField("String", "TOKEN", "\"$token\"")
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
    }

    buildFeatures {
        buildConfig = true
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

kover {
    reports {
        filters {
            excludes {
                androidGeneratedClasses()
                classes(
                    "*_Factory",
                    "*_HiltComponents",
                )
            }
        }
    }
}

dependencies {
    implementation(projects.features.domain)
    implementation(projects.core.common)
    // Coroutine
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    // Hilt
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)
    // Network
    api(libs.retrofit.core)
    implementation(libs.retrofit.kotlinx.serialization.converter)
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.logging.interceptor)
    api(libs.kotlin.serialization.json)
}
