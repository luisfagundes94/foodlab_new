package com.luisfagundes.data.network.response

data class RecipeResponse(
    val id: Int,
    val title: String,
    val image: String,
    val servings: Int,
    val readyInMinutes: Int,
    val sourceUrl: String?,
    val aggregateLikes: Int,
    val spoonacularScore: Int,
    val sourceName: String?,
    val healthScore: Int,
    val cheap: Boolean,
    val vegetarian: Boolean,
    val vegan: Boolean,
    val dishTypes: List<String>?,
    val summary: String
)
