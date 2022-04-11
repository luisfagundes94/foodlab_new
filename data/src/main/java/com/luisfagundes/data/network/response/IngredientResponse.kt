package com.luisfagundes.data.network.response

data class IngredientResponse(
    val id: Int,
    val amount: Float,
    val name: String,
    val image: String?,
    val measures: MeasuresResponse
)