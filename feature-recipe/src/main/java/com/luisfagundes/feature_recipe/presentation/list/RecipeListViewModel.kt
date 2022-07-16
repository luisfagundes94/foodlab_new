package com.luisfagundes.feature_recipe.presentation.list

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.luisfagundes.base.BasePagingViewModel
import com.luisfagundes.domain.model.RecipeIntro
import com.luisfagundes.usecases.GetRecipes
import com.luisfagundes.utils.GlobalConstants.RecipeListKeyParams.SORT
import com.luisfagundes.utils.GlobalConstants.RecipeListValueParams.POPULARITY
import kotlinx.coroutines.flow.Flow

class RecipeListViewModel(
    private val getRecipes: GetRecipes
) : BasePagingViewModel() {

    fun getRecipesPagingData(): Flow<PagingData<RecipeIntro>> {
        val queryParams = hashMapOf<String, String>()
        queryParams[SORT] = POPULARITY

        return getRecipes.invoke(
            GetRecipes.GetRecipesParams(queryParams, getPageConfig())
        ).cachedIn(viewModelScope)
    }
}