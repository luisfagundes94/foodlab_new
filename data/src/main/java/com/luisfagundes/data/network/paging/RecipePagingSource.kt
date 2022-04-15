package com.luisfagundes.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.luisfagundes.data.network.response.DataContainerResponse
import com.luisfagundes.data.network.response.toDomainModel
import com.luisfagundes.domain.datasource.RecipeRemoteDataSource
import com.luisfagundes.domain.model.Recipe

class RecipePagingSource(
    private val remoteDataSource: RecipeRemoteDataSource<DataContainerResponse>,
    private val queryMap: HashMap<String, String>
) : PagingSource<Int, Recipe>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        return try {
            getLoadResult(params)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    private suspend fun getLoadResult(params: LoadParams<Int>): LoadResult.Page<Int, Recipe> {
        val offset = params.key ?: DEFAULT_OFFSET
        val queries = hashMapOf(OFFSET_KEY to offset.toString())

        queries.putAll(queryMap)

        val response = remoteDataSource.fetchRecipes(queries)
        val responseOffset = response.offset
        val totalRecipes = response.totalResults

        return LoadResult.Page(
            data = response.results.map { it.toDomainModel() },
            prevKey = null,
            nextKey = calculateNextKey(responseOffset, totalRecipes)
        )
    }

    private fun calculateNextKey(responseOffset: Int, totalRecipes: Int) =
        if (responseOffset < totalRecipes) responseOffset + RESULTS_PER_PAGE
        else null

    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(RESULTS_PER_PAGE) ?: anchorPage?.nextKey?.minus(
                RESULTS_PER_PAGE
            )
        }
    }

    private companion object {
        const val DEFAULT_OFFSET = 0
        const val OFFSET_KEY = "offset"
        const val RESULTS_PER_PAGE = 10
    }
}