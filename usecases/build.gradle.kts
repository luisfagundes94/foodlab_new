import com.luisfagundes.buildSrc.Dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32
}

dependencies {
    // Modules
    implementation(project(":domain"))
    implementation(project(":commons-testing"))
    implementation(project(":base"))
    implementation(project(":core"))

    // DI
    implementation(Dependencies.DI.koin)
    implementation(Dependencies.DI.koinCore)

    // Core
    implementation(Dependencies.Core.paging3)
}