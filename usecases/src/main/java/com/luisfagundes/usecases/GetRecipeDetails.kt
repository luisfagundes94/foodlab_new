package com.luisfagundes.usecases

import com.luisfagundes.base.BaseUseCase
import com.luisfagundes.core.ResultStatus
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.domain.repository.RecipeRepository

class GetRecipeDetails(
    private val repository: RecipeRepository
) : BaseUseCase<GetRecipeDetails.GetRecipeDetailsParams, Recipe>() {

    data class GetRecipeDetailsParams(
        val id: Int
    )

    override suspend fun doWork(
        params: GetRecipeDetailsParams
    ): ResultStatus<Recipe> {
        val result = repository.fetchRecipeDetails(params.id)
        return ResultStatus.Success(result)
    }
}