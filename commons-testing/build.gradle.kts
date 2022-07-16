import com.luisfagundes.buildSrc.Dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32
}

dependencies {
    api(project(":domain"))
    api(Dependencies.Test.junit4)
    api(Dependencies.Test.mockk)
    api(Dependencies.Test.coroutines)
}