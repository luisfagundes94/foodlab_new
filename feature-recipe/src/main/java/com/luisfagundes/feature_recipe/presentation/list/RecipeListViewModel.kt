package com.luisfagundes.feature_recipe.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luisfagundes.base.BaseViewModel
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.domain.usecase.GetRecipes
import com.luisfagundes.feature_recipe.model.RecipesUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import java.lang.Exception

class RecipeListViewModel(
    private val getRecipes: GetRecipes,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel() {

    private val _recipesUiState = MutableLiveData<RecipesUiState>()
    val recipesUiState: LiveData<RecipesUiState> = _recipesUiState

    fun getRecipes() {
        val queryParams = hashMapOf<String, String>()
        queryParams["addRecipeInformation"] = true.toString()
        queryParams["sort"] = "popularity"

        executeCoroutines(dispatcher) {
            getRecipes.invoke(queryParams).fold(
                ::onGetRecipesSuccess, ::onGetRecipesError
            )
        }
    }

    private fun onGetRecipesSuccess(recipes: List<Recipe>) {
        _recipesUiState.postValue(RecipesUiState.Success(recipes))
    }

    private fun onGetRecipesError(exception: Exception) {
        Timber.e(exception)
        _recipesUiState.postValue(RecipesUiState.Error)
    }
}