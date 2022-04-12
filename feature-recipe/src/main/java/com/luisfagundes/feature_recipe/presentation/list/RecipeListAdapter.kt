package com.luisfagundes.feature_recipe.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.extensions.load
import com.luisfagundes.feature_recipe.databinding.LayoutRecipeItemBinding
import com.luisfagundes.feature_recipe.extensions.formatToMinutes

class RecipeListAdapter(
    private val navigateToRecipeDetail: (recipeId: Int) -> Unit
): RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    private val recipes = mutableListOf<Recipe>()

    fun updateRecipes(recipes: List<Recipe>) {
        this.recipes.clear()
        this.recipes.addAll(recipes)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutRecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, navigateToRecipeDetail)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount() = recipes.count()

    class ViewHolder(
        private val binding: LayoutRecipeItemBinding,
        private val navigateToRecipeDetail: (recipeId: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) = with(binding) {
            imgRecipe.load(recipe.image)
            tvTitle.text = recipe.title
            tvRating.text = recipe.spoonacularScore.toString()
            tvRecipeOwner.text = recipe.sourceName
            tvReadyInMinutes.text = recipe.readyInMinutes.formatToMinutes()
            rootContainer.setOnClickListener { navigateToRecipeDetail(recipe.id) }
        }
    }
}