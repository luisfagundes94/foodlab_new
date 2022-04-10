package com.luisfagundes.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterception(
    private val apiKey: String
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestUrl = request.url
        val newUrl = requestUrl.newBuilder()
            .addQueryParameter(API_KEY, apiKey)
            .addQueryParameter(LIMIT_LICENSE, true.toString())
            .addQueryParameter(NUMBER_OF_RESULTS, 20.toString())
            .build()

        return chain.proceed(
            request.newBuilder()
                .url(newUrl)
                .build()
        )
    }

    private companion object {
        const val API_KEY = "apiKey"
        const val LIMIT_LICENSE = "limitLicense"
        const val NUMBER_OF_RESULTS = "number"
    }
}