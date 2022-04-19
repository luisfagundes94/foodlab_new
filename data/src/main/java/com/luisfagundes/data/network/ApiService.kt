package com.luisfagundes.data.network

import com.luisfagundes.data.network.response.DataContainerResponse
import com.luisfagundes.data.network.response.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {
    @GET("recipes/complexSearch")
    suspend fun fetchRecipes(
        @QueryMap
        queries: Map<String, String>,
        @Query("instructionsRequired") instructionsRequired: Boolean = true
    ): DataContainerResponse

    @GET("recipes/{id}/information")
    suspend fun fetchRecipeDetails(
        @Path("id") id: Int
    ): RecipeResponse
}