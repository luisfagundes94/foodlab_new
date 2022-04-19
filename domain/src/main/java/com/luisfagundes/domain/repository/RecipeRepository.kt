package com.luisfagundes.domain.repository

import androidx.paging.PagingSource
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.domain.model.RecipeIntro

interface RecipeRepository {
    fun fetchRecipes(queryMap: HashMap<String, String>): PagingSource<Int, RecipeIntro>
    suspend fun fetchRecipeDetails(id: Int): Recipe
}