package com.luisfagundes.domain.model

data class RecipeListPaging(
    val offset: Int,
    val totalResults: Int,
    val results: List<Recipe>
)
