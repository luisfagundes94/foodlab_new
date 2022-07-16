import com.luisfagundes.buildSrc.Dependencies
import com.luisfagundes.buildSrc.Versions

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
    implementation(project(":data"))
    implementation(project(":commons-testing"))
    implementation(project(":usecases"))

    // Lifecycle
    implementation(Dependencies.Lifecycle.runtime)

    // UI
    implementation(Dependencies.UI.shimmerEffect)
    implementation(Dependencies.UI.picasso)
    api("androidx.paging:paging-runtime-ktx:3.1.1")
}