package com.luisfagundes.data.network.response

data class DataContainerResponse(
    val offset: Int,
    val number: Int,
    val totalResults: Int,
    val results: List<RecipeResponse>
)
