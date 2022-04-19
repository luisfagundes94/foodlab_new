package com.luisfagundes.feature_recipe.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisfagundes.domain.model.RecipeIntro
import com.luisfagundes.extensions.load
import com.luisfagundes.feature_recipe.R
import com.luisfagundes.feature_recipe.databinding.LayoutRecipeItemBinding

class RecipeListViewHolder(
    private val binding: LayoutRecipeItemBinding,
    private val navigateToRecipeDetail: (recipeId: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(recipe: RecipeIntro) = with(binding) {
        imgRecipe.load(recipe.image)
        tvTitle.text = recipe.title
        tvCalories.text = root.context.getString(R.string.calories, recipe.calories)
        rootContainer.setOnClickListener { navigateToRecipeDetail(recipe.id) }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            navigateToRecipeDetail: (recipeId: Int) -> Unit
        ): RecipeListViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = LayoutRecipeItemBinding.inflate(inflater, parent, false)
            return RecipeListViewHolder(binding, navigateToRecipeDetail)
        }
    }
}