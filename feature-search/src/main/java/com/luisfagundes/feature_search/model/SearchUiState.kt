package com.luisfagundes.feature_search.model

import com.luisfagundes.domain.model.Recipe

sealed class SearchUiState {
    data class Success(val recipes: List<Recipe>): SearchUiState()
    object Loading: SearchUiState()
    object Error: SearchUiState()
}
