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
        libs.plugins.dataNameSpace
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
    implementation(projects.core.network)
    implementation(projects.core.common)
    // Coroutine
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    // Hilt
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)
    // Testing
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockwebserver)
    testImplementation(libs.retrofit.kotlinx.serialization.converter)
    testImplementation(libs.strikt.core)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.jupiter.engine)
}
