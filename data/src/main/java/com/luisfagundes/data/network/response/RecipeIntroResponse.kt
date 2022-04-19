package com.luisfagundes.data.network.response

import com.luisfagundes.domain.model.RecipeIntro

data class RecipeIntroResponse(
    val id: Int,
    val title: String,
    val image: String
)

fun RecipeIntroResponse.toDomainModel() =
    RecipeIntro(
        id = this.id,
        title = this.title,
        image = this.image
    )
