package com.luisfagundes.feature_recipe.model

import com.luisfagundes.domain.model.Recipe

sealed class RecipesUiState {
    data class Success(val recipes: List<Recipe>): RecipesUiState()
    object Loading: RecipesUiState()
    object Error: RecipesUiState()
}
