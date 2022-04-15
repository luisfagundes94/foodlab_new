import com.luisfagundes.buildSrc.Versions
import com.luisfagundes.buildSrc.Dependencies
import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

val apiKeyPropertiesFile = rootProject.file("apikey.properties")
val apiKeyProperties = Properties()
apiKeyProperties.load(FileInputStream(apiKeyPropertiesFile))

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "API_KEY", "\"${getApiKey()}\"")
        buildConfigField("String", "BASE_URL", "\"https://api.spoonacular.com/\"")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // Modules
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":extensions"))

    // Data
    implementation(Dependencies.Data.retrofit)
    implementation(Dependencies.Data.retrofitGson)
    implementation(Dependencies.Data.okHttpLogging)
    implementation(Dependencies.Data.okHttp)
    implementation(Dependencies.Data.loggingInterceptor)
    api(Dependencies.Data.paging3)

    // DI
    implementation(Dependencies.DI.koin)
    implementation(Dependencies.DI.koinCore)
}

fun getApiKey() = apiKeyProperties["API_KEY"]