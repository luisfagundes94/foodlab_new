package com.luisfagundes.domain.datasource

import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.domain.model.RecipeListPaging

interface RecipeRemoteDataSource {
    suspend fun fetchRecipes(queries: HashMap<String, String>): RecipeListPaging
    suspend fun fetchRecipeDetails(id: Int): Recipe
}