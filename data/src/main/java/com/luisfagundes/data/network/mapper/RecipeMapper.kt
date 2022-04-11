package com.luisfagundes.data.network.mapper

import com.luisfagundes.core.Response
import com.luisfagundes.data.network.response.RecipeResponse
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.extensions.empty

object RecipeMapper {
    fun Response<List<RecipeResponse>>.mapToDomain(): Response<List<Recipe>> {
        return when (this) {
            is Response.Success -> mapToDomain()
            is Response.Error -> Response.Error(exception)
        }
    }

    private fun Response.Success<List<RecipeResponse>>.mapToDomain() =
        Response.Success(this.data.map { it.toDomain() })

    private fun RecipeResponse.toDomain() =
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
            sourceName = this.sourceName ?: String.empty()
        )
}