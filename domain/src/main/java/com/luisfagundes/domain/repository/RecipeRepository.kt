package com.luisfagundes.domain.repository

import androidx.paging.PagingSource
import com.luisfagundes.domain.model.Recipe

interface RecipeRepository {
    fun fetchRecipes(queryMap: HashMap<String, String>): PagingSource<Int, Recipe>
}