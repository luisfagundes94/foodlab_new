package com.luisfagundes.domain.usecase

import com.luisfagundes.core.Response
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.domain.repository.RecipeRepository

class GetRecipeDetails(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(recipeId: Int): Response<Recipe> =
        recipeRepository.fetchRecipeDetails(recipeId)
}