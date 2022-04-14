package com.luisfagundes.feature_recipe.presentation.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.luisfagundes.domain.model.Recipe

class RecipeListAdapter(
    private val navigateToRecipeDetail: (recipeId: Int) -> Unit
): ListAdapter<Recipe, RecipeListViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        return RecipeListViewHolder.create(parent, navigateToRecipeDetail)
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        holder.bind(getItem(position))
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