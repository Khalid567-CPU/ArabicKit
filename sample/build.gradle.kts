plugins {
    id("com.android.application")
}

android {
    namespace = "io.github.khalid567cpu.arabickit.sample"
    compileSdk = 37

    defaultConfig {
        applicationId = "io.github.khalid567cpu.arabickit.sample"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "0.1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true
    }

    lint {
        abortOnError = true
        warningsAsErrors = false
        checkReleaseBuilds = true
        disable += setOf(
            "AndroidGradlePluginVersion",
            "DataExtractionRules",
            "MissingApplicationIcon",
            "Autofill",
        )
    }
}

dependencies {
    implementation(project(":arabickit"))
}