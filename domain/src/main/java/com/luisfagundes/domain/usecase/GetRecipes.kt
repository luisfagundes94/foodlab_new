package com.luisfagundes.domain.usecase

import com.luisfagundes.domain.repository.RecipeRepository

class GetRecipes(private val repository: RecipeRepository) {
    suspend operator fun invoke(queryMap: Map<String, String>) = repository.fetchRecipes(queryMap)
}