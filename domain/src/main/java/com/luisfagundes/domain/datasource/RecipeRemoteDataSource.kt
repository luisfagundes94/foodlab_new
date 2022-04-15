package com.luisfagundes.domain.datasource

interface RecipeRemoteDataSource<T> {
    suspend fun fetchRecipes(queries: HashMap<String, String>): T
}