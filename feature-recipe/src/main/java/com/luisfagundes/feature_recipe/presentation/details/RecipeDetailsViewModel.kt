package com.luisfagundes.feature_recipe.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luisfagundes.base.BaseViewModel
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.domain.usecase.GetRecipeDetails
import com.luisfagundes.feature_recipe.model.RecipeDetailsState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class RecipeDetailsViewModel(
    private val useCase: GetRecipeDetails,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel() {

    private val _recipeState = MutableLiveData<RecipeDetailsState>()
    val recipe: LiveData<RecipeDetailsState> = _recipeState

    fun checkIfInstructionsAreNotEmpty(instructions: String, action: () -> Unit) {
        if (instructions.isNotEmpty()) action.invoke()
    }

    fun fetchRecipeDetails(recipeId: Int) {
        _recipeState.postValue(RecipeDetailsState.Loading)
        executeCoroutines(dispatcher) {
            useCase(recipeId).fold(::onSuccess, ::onError)
        }
    }

    private fun onSuccess(recipe: Recipe) {
        _recipeState.postValue(RecipeDetailsState.Success(recipe))
    }

    private fun onError(exception: Exception) {
        Timber.e(exception)
        _recipeState.postValue(RecipeDetailsState.Error)
    }
}