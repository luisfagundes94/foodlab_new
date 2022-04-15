package com.luisfagundes.data.network.di

import com.luisfagundes.data.BuildConfig
import com.luisfagundes.data.network.ApiService
import com.luisfagundes.data.network.interceptor.AuthInterception
import com.luisfagundes.data.network.repository.RecipeRepositoryImpl
import com.luisfagundes.data.network.response.DataContainerResponse
import com.luisfagundes.data.remote.RemoteRecipeDataSourceImpl
import com.luisfagundes.domain.datasource.RecipeRemoteDataSource
import com.luisfagundes.domain.repository.RecipeRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 15L

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val networkModule = module {

    single { RemoteRecipeDataSourceImpl(get()) as RecipeRemoteDataSource<DataContainerResponse> }

    single { RecipeRepositoryImpl(get()) as RecipeRepository }

    single { createApiService(get()) }

    single { createRetrofit(get(), get()) }

    single { createHttpClient(get(), get()) }

    single { createAuthInterceptor() }

    single { createHttpInterceptor() }

    single { GsonConverterFactory.create() }
}

private fun createApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

private fun createRetrofit(
    okHttpClient: OkHttpClient,
    converterFactory: GsonConverterFactory
) = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(converterFactory)
    .build()

private fun createHttpClient(
    authInterception: AuthInterception,
    httpLoggingInterceptor: HttpLoggingInterceptor
) =
    OkHttpClient.Builder()
        .addInterceptor(authInterception)
        .addInterceptor(httpLoggingInterceptor)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .build()

private fun createAuthInterceptor() = AuthInterception(apiKey = BuildConfig.API_KEY)

private fun createHttpInterceptor() = HttpLoggingInterceptor().apply {
    setLevel(
        when {
            BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
    )
}
