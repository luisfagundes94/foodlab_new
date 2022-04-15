package com.luisfagundes.feature_recipe.presentation.list

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.luisfagundes.domain.model.Recipe

class RecipeListAdapter(
    private val navigateToRecipeDetail: (recipeId: Int) -> Unit
): PagingDataAdapter<Recipe, RecipeListViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        return RecipeListViewHolder.create(parent, navigateToRecipeDetail)
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(
                oldItem: Recipe,
                newItem: Recipe
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Recipe,
                newItem: Recipe
            ) = oldItem == newItem
        }
    }
}