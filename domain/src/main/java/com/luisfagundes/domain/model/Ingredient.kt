package com.luisfagundes.domain.model

data class Ingredient(
    val id: Int,
    val name: String,
    val amount: Float,
    val image: String?,
    val measures: Measures
)
