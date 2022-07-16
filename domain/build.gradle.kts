import com.luisfagundes.buildSrc.Dependencies

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":core"))

    // Data
    implementation(Dependencies.Core.paging3)

    // Core
    implementation(Dependencies.Core.coroutinesCore)

    // Testing
    implementation(Dependencies.Test.junit4)
    implementation(Dependencies.Test.mockk)
    implementation(Dependencies.Test.coroutines)
}
