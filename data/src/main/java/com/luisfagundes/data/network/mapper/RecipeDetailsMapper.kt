package com.luisfagundes.data.network.mapper

import com.luisfagundes.core.Response
import com.luisfagundes.data.network.response.IngredientResponse
import com.luisfagundes.data.network.response.MeasureResponse
import com.luisfagundes.data.network.response.MeasuresResponse
import com.luisfagundes.data.network.response.RecipeResponse
import com.luisfagundes.domain.model.Ingredient
import com.luisfagundes.domain.model.Measure
import com.luisfagundes.domain.model.Measures
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.extensions.empty

private const val BASE_IMAGE_URL = "https://spoonacular.com/cdn/ingredients_100x100/"

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

    private fun List<IngredientResponse>.mapToDomain(): List<Ingredient> {
        return this.map { it.toDomain() }
    }

    private fun IngredientResponse.toDomain(): Ingredient {
        return Ingredient(
            id = this.id,
            amount = this.amount,
            name = this.name,
            image = BASE_IMAGE_URL + this.image,
            measures = this.measures.mapToDomain()
        )
    }

    private fun MeasuresResponse.mapToDomain(): Measures {
        return Measures(
            metric = this.metric.toDomain(),
            us = this.us.toDomain()
        )
    }

    private fun MeasureResponse.toDomain(): Measure {
        return Measure(
            amount = this.amount,
            unitLong = this.unitLong,
            unitShort = this.unitShort
        )
    }
}