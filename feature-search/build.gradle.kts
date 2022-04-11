import com.luisfagundes.buildSrc.Versions
import com.luisfagundes.buildSrc.Dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
}

apply(from = "${rootProject.rootDir}/base_dependencies.gradle")

android {
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        minSdk = Versions.minSdk
    }
}

dependencies {

    // Modules
    implementation(project(":domain"))
    implementation(project(":feature-recipe"))

    // UI
    implementation(Dependencies.UI.shimmerEffect)
    implementation(Dependencies.UI.picasso)
}