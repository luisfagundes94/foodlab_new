package com.luisfagundes.feature_search.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.luisfagundes.base.BasePagingViewModel
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.domain.usecase.GetRecipes
import kotlinx.coroutines.flow.Flow

class SearchViewModel(
    private val getRecipes: GetRecipes
): BasePagingViewModel() {

    fun fetchRecipesPagingDataByQuery(query: String): Flow<PagingData<Recipe>> {
        val queryParams = hashMapOf<String, String>()
        queryParams["addRecipeInformation"] = true.toString()
        queryParams["query"] = query

        return getRecipes.invoke(
            GetRecipes.GetRecipesParams(queryParams, getPageConfig())
        ).cachedIn(viewModelScope)
    }
}