package com.luisfagundes.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.luisfagundes.base.BasePagingUseCase
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class GetRecipes(
    private val repository: RecipeRepository
) : BasePagingUseCase<GetRecipes.GetRecipesParams, Recipe>() {

    data class GetRecipesParams(
        val query: HashMap<String, String>,
        val pagingConfig: PagingConfig
    )

    override fun createFlowObservable(params: GetRecipesParams): Flow<PagingData<Recipe>> {
        return Pager(config = params.pagingConfig) {
            repository.fetchRecipes(params.query)
        }.flow
    }
}