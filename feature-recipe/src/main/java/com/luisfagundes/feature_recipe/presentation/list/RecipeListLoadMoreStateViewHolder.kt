package com.luisfagundes.feature_recipe.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.luisfagundes.feature_recipe.databinding.RecipeItemLoadMoreStateBinding

class RecipeListLoadMoreStateViewHolder(
    binding: RecipeItemLoadMoreStateBinding,
    private val retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private val progressBarLoadMore = binding.progressBarLoadMore
    private val tryAgainButton = binding.btnTryAgain.also {
        it.setOnClickListener {
            retry()
        }
    }

    fun bind(loadState: LoadState) {
        progressBarLoadMore.isVisible = loadState is LoadState.Loading
        tryAgainButton.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): RecipeListLoadMoreStateViewHolder {
            val binding = RecipeItemLoadMoreStateBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return RecipeListLoadMoreStateViewHolder(binding, retry)
        }
    }
}