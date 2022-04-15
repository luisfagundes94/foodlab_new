package com.luisfagundes.data.network.response

import com.luisfagundes.data.network.mapper.IngredientMapper.mapToDomain
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.extensions.empty

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
    val extendedIngredients: List<IngredientResponse>?,
    val vegetarian: Boolean,
    val vegan: Boolean,
    val dishTypes: List<String>?,
    val summary: String
)

fun RecipeResponse.toDomainModel() =
    Recipe(
        id = this.id,
        title = this.title,
        image = this.image,
        servings = this.servings,
        readyInMinutes = this.readyInMinutes,
        sourceUrl = this.sourceUrl,
        aggregateLikes = this.aggregateLikes,
        spoonacularScore = this.spoonacularScore,
        healthScore = this.healthScore,
        cheap = this.cheap,
        vegetarian = this.vegetarian,
        vegan = this.vegan,
        dishTypes = this.dishTypes ?: emptyList(),
        summary = this.summary,
        sourceName = this.sourceName ?: String.empty(),
        ingredients = this.extendedIngredients?.mapToDomain() ?: emptyList()
    )
