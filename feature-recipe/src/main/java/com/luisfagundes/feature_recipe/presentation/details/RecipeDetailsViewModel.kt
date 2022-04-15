package com.luisfagundes.feature_recipe.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luisfagundes.base.BaseViewModel
import com.luisfagundes.core.ResultStatus
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.domain.usecase.GetRecipeDetails
import com.luisfagundes.feature_recipe.model.RecipeDetailsState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class RecipeDetailsViewModel(
    private val getRecipeDetails: GetRecipeDetails,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel() {

    private val _uiState = MutableLiveData<RecipeDetailsState>()
    val uiState: LiveData<RecipeDetailsState> = _uiState

    fun fetchRecipeDetails(id: Int) = executeCoroutines(dispatcher) {
        getRecipeDetails(GetRecipeDetails.GetRecipeDetailsParams(id))
            .observeStatus()
    }

    private suspend fun Flow<ResultStatus<Recipe>>.observeStatus() {
        collect { status ->
            val currentStatus = when (status) {
                is ResultStatus.Loading -> RecipeDetailsState.Loading
                is ResultStatus.Success -> RecipeDetailsState.Success(status.data)
                is ResultStatus.Error -> RecipeDetailsState.Error
            }
            _uiState.postValue(currentStatus)
        }
    }
}