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
            sourceName = this.sourceName ?: String.empty(),
            ingredients = this.extendedIngredients?.mapToDomain() ?: emptyList()
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