package com.luisfagundes.feature_search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luisfagundes.base.BaseViewModel
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.domain.usecase.SearchRecipes
import com.luisfagundes.feature_search.model.SearchUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class SearchViewModel(
    private val searchRecipes: SearchRecipes,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): BaseViewModel() {
    private val _searchUiState = MutableLiveData<SearchUiState>()
    val searchUiState: LiveData<SearchUiState> = _searchUiState

    fun fetchRecipesByQuery(query: String) {
        val queryParams = hashMapOf<String, String>()
        queryParams["addRecipeInformation"] = true.toString()
        queryParams["query"] = query

        _searchUiState.postValue(SearchUiState.Loading)
//        executeCoroutines(dispatcher) {
//            searchRecipes(queryParams)
//                .fold(::onFetchRecipesSuccess, ::onFetchRecipesFailure)
//        }
    }

    private fun onFetchRecipesSuccess(recipes: List<Recipe>) {
        _searchUiState.postValue(SearchUiState.Success(recipes))
    }

    private fun onFetchRecipesFailure(exception: Exception) {
        Timber.e(exception.message.toString())
        _searchUiState.postValue(SearchUiState.Error)
    }
}