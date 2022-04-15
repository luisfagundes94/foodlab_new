package com.luisfagundes.data.network.repository

import androidx.paging.PagingSource
import com.luisfagundes.core.Response
import com.luisfagundes.data.network.ApiService
import com.luisfagundes.data.network.mapper.RecipeDetailsMapper.mapToDomain
import com.luisfagundes.data.network.mapper.RecipeMapper.mapToDomain
import com.luisfagundes.data.network.paging.RecipePagingSource
import com.luisfagundes.data.network.response.DataContainerResponse
import com.luisfagundes.domain.datasource.RecipeRemoteDataSource
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val remoteDataSource: RecipeRemoteDataSource<DataContainerResponse>
) : RecipeRepository {
    override fun fetchRecipes(queryMap: HashMap<String, String>): PagingSource<Int, Recipe> {
        return RecipePagingSource(remoteDataSource, queryMap)
    }
}