package com.luisfagundes.buildSrc

object Dependencies {

    object Core {
        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    }

    object Data {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val okHttp = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
        const val preferencesKtx = "androidx.preference:preference-ktx:${Versions.preferencesKtx}"
        const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val loggingInterceptor = "com.github.ihsanbal:LoggingInterceptor:${Versions.loggingInterceptor}"
    }

    object UI {
        const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
        const val cardView = "androidx.cardview:cardview:${Versions.card_view}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        const val lottieAnimation = "com.airbnb.android:lottie:${Versions.lottieAnimation}"
        const val shimmerEffect = "com.facebook.shimmer:shimmer:${Versions.shimmer}"
        const val fadingEdgeLayout = "com.github.bosphere.android-fadingedgelayout:fadingedgelayout:${Versions.fadingEdgeLayout}"
        const val flexboxLayout = "com.google.android.flexbox:flexbox:${Versions.flexboxLayout}"
    }

    object DI {
        const val koin = "io.insert-koin:koin-android:${Versions.koin}"
        const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
        const val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
        const val daggerHiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"
    }

    object Gradle {
        const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val ktlintPlugin = "com.pinterest:ktlint:${Versions.ktlint}"
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
        const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHilt}"
        const val kotlinx = "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.kotlin}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.junit}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
        const val archCore = "androidx.arch.core:core-testing:${Versions.archCore}"
    }

    object Lifecycle {
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val viewModel = "androidx.lifecycle.lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    }
}
