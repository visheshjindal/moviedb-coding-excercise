plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp.plugin)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.android.junit5)
    alias(libs.plugins.kover.plugin)
}

android {
    namespace = libs.plugins.domainNameSpace.get().toString()
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
                    "_*",
                    "dagger.hilt.internal.aggregatedroot.codegen.*",
                    "hilt_aggregated_deps.*",
                    "*ComposableSingletons*",
                    "*_HiltModules*",
                    "*Hilt_*",
                    "*BuildConfig",
                    ".*_Factory.*",
                )
            }
        }
    }
}

dependencies {
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
    testImplementation(libs.strikt.core)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.jupiter.engine)
}
