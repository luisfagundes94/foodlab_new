package com.luisfagundes.data.network.mapper

import com.luisfagundes.core.Response
import com.luisfagundes.data.network.mapper.IngredientMapper.mapToDomain
import com.luisfagundes.data.network.response.RecipeResponse
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.extensions.empty

object RecipeDetailsMapper {
    fun Response<RecipeResponse>.mapToDomain(): Response<Recipe> {
        return when (this) {
            is Response.Success -> toDomain()
            is Response.Error -> Response.Error(exception)
        }
    }

    private fun Response.Success<RecipeResponse>.toDomain() =
        Response.Success(
            Recipe(
                id = this.data.id,
                title = this.data.title,
                image = this.data.image,
                servings = this.data.servings,
                readyInMinutes = this.data.readyInMinutes,
                sourceUrl = this.data.sourceUrl,
                aggregateLikes = this.data.aggregateLikes,
                spoonacularScore = this.data.spoonacularScore,
                healthScore = this.data.healthScore,
                cheap = this.data.cheap,
                vegetarian = this.data.vegetarian,
                vegan = this.data.vegan,
                dishTypes = this.data.dishTypes ?: emptyList(),
                summary = this.data.summary,
                sourceName = this.data.sourceName ?: String.empty(),
                ingredients = this.data.extendedIngredients?.mapToDomain() ?: emptyList()
            )
        )
}