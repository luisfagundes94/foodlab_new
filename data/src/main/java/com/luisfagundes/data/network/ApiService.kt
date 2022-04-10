package com.luisfagundes.data.network

import com.luisfagundes.data.network.response.DataContainerResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET("recipes/complexSearch")
    suspend fun fetchRecipes(
        @QueryMap
        queries: Map<String, String>
    ): DataContainerResponse
}