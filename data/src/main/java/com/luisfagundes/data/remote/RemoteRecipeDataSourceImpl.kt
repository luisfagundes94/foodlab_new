package com.luisfagundes.data.remote

import com.luisfagundes.data.network.ApiService
import com.luisfagundes.data.network.response.toDomainModel
import com.luisfagundes.domain.datasource.RecipeRemoteDataSource
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.domain.model.RecipeListPaging

class RemoteRecipeDataSourceImpl(
    private val apiService: ApiService
) : RecipeRemoteDataSource {
    override suspend fun fetchRecipes(queries: HashMap<String, String>): RecipeListPaging {
        val data = apiService.fetchRecipes(queries)
        val recipes = data.results.map { it.toDomainModel() }

        return RecipeListPaging(
            data.offset,
            data.totalResults,
            recipes
        )
    }

    override suspend fun fetchRecipeDetails(id: Int): Recipe {
        val data = apiService.fetchRecipeDetails(id)
        return data.toDomainModel()
    }
}