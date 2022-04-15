package com.luisfagundes.feature_recipe.presentation.list

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.luisfagundes.base.BasePagingViewModel
import com.luisfagundes.base.BaseViewModel
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.domain.usecase.GetRecipes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class RecipeListViewModel(
    private val getRecipes: GetRecipes
) : BasePagingViewModel() {

    fun getRecipesPagingData(): Flow<PagingData<Recipe>> {
        val queryParams = hashMapOf<String, String>()
        queryParams["addRecipeInformation"] = true.toString()
        queryParams["sort"] = "popularity"

        return getRecipes.invoke(
            GetRecipes.GetRecipesParams(queryParams, getPageConfig())
        ).cachedIn(viewModelScope)
    }
}