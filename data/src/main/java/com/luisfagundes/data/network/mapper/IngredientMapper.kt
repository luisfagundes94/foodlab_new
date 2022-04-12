package com.luisfagundes.data.network.mapper

import com.luisfagundes.data.network.response.IngredientResponse
import com.luisfagundes.data.network.response.MeasureResponse
import com.luisfagundes.data.network.response.MeasuresResponse
import com.luisfagundes.domain.model.Ingredient
import com.luisfagundes.domain.model.Measure
import com.luisfagundes.domain.model.Measures

private const val BASE_IMAGE_URL = "https://spoonacular.com/cdn/ingredients_100x100/"

object IngredientMapper {
    fun List<IngredientResponse>.mapToDomain(): List<Ingredient> {
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