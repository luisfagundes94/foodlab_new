package com.luisfagundes.feature_recipe.model

import com.luisfagundes.domain.model.Recipe

sealed class RecipeDetailsState {
    data class Success(val data: Recipe): RecipeDetailsState()
    object Loading: RecipeDetailsState()
    object Error: RecipeDetailsState()
}
