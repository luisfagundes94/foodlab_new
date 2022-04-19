package com.luisfagundes.feature_recipe.presentation.list

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.domain.model.RecipeIntro

class RecipeListAdapter(
    private val navigateToRecipeDetail: (recipeId: Int) -> Unit
) : PagingDataAdapter<RecipeIntro, RecipeListViewHolder>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = RecipeListViewHolder.create(parent, navigateToRecipeDetail)


    override fun onBindViewHolder(
        holder: RecipeListViewHolder,
        position: Int
    ) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<RecipeIntro>() {
            override fun areItemsTheSame(
                oldItem: RecipeIntro,
                newItem: RecipeIntro
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: RecipeIntro,
                newItem: RecipeIntro
            ) = oldItem == newItem
        }
    }
}