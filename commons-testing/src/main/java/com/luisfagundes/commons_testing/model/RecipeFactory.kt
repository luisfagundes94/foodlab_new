package com.luisfagundes.commons_testing.model

import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.domain.model.RecipeIntro

class RecipeFactory {
    fun createIntro() = RecipeIntro(
        id = RANDOM_NUMBER,
        title = TITLE,
        image = IMAGE
    )

    fun create() = Recipe(
        id = RANDOM_NUMBER,
        title = TITLE,
        image = IMAGE,
        servings = RANDOM_NUMBER,
        readyInMinutes = RANDOM_NUMBER,
        sourceUrl = "",
        aggregateLikes = RANDOM_NUMBER,
        spoonacularScore = RANDOM_NUMBER,
        healthScore = RANDOM_NUMBER,
        cheap = false,
        ingredients = emptyList(),
        vegetarian = false,
        vegan = false,
        dishTypes = emptyList(),
        summary = "",
        sourceName = ""
    )

    private companion object {
        const val RANDOM_NUMBER = 1
        const val TITLE = "TITLE"
        const val IMAGE = "IMAGE"
    }
}