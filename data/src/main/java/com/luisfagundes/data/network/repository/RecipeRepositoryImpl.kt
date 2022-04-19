package com.luisfagundes.data.network.repository

import androidx.paging.PagingSource
import com.luisfagundes.data.network.paging.RecipePagingSource
import com.luisfagundes.domain.datasource.RecipeRemoteDataSource
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.domain.model.RecipeIntro
import com.luisfagundes.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val remoteDataSource: RecipeRemoteDataSource
) : RecipeRepository {
    override fun fetchRecipes(queryMap: HashMap<String, String>): PagingSource<Int, RecipeIntro> {
        return RecipePagingSource(remoteDataSource, queryMap)
    }

    override suspend fun fetchRecipeDetails(id: Int): Recipe {
        return remoteDataSource.fetchRecipeDetails(id)
    }
}