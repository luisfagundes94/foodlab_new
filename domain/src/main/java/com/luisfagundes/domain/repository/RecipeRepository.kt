package com.luisfagundes.domain.repository

import com.luisfagundes.core.Response
import com.luisfagundes.domain.model.Recipe

interface RecipeRepository {
    suspend fun fetchRecipes(queryMap: Map<String, String>): Response<List<Recipe>>
}