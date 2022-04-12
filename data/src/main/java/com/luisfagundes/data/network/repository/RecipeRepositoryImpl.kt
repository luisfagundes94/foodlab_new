package com.luisfagundes.data.network.repository

import com.luisfagundes.core.Response
import com.luisfagundes.data.network.ApiService
import com.luisfagundes.data.network.mapper.RecipeDetailsMapper.mapToDomain
import com.luisfagundes.data.network.mapper.RecipeMapper.mapToDomain
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val apiService: ApiService
) : RecipeRepository {
    override suspend fun fetchRecipes(queryMap: Map<String, String>): Response<List<Recipe>> {
        val response = Response.listOf {
            apiService.fetchRecipes(queryMap).results
        }
        return response.mapToDomain()
    }

    override suspend fun fetchRecipeDetails(id: Int): Response<Recipe> {
        val response = Response.of {
            apiService.fetchRecipeDetails(id)
        }
        return response.mapToDomain()
    }
}