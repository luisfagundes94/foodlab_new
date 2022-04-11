package com.luisfagundes.domain.usecase

import com.luisfagundes.core.Response
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.domain.repository.RecipeRepository

class SearchRecipes(private val repository: RecipeRepository) {
    suspend operator fun invoke(queryMap: Map<String, String>): Response<List<Recipe>> {
        return repository.fetchRecipes(queryMap)
    }
}