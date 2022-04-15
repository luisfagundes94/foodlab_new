package com.luisfagundes.feature_recipe.presentation.list

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class RecipeListLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<RecipeListLoadMoreStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = RecipeListLoadMoreStateViewHolder.create(parent, retry)


    override fun onBindViewHolder(
        holder: RecipeListLoadMoreStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)
}