package com.luisfagundes.data.remote

import com.luisfagundes.data.network.ApiService
import com.luisfagundes.data.network.response.DataContainerResponse
import com.luisfagundes.domain.datasource.RecipeRemoteDataSource

class RemoteRecipeDataSourceImpl(
    private val apiService: ApiService
) : RecipeRemoteDataSource<DataContainerResponse> {
    override suspend fun fetchRecipes(queries: HashMap<String, String>): DataContainerResponse {
        return apiService.fetchRecipes(queries)
    }
}