package com.luisfagundes.feature_recipe.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisfagundes.domain.model.Ingredient
import com.luisfagundes.extensions.load
import com.luisfagundes.feature_recipe.databinding.LayoutIngredientItemBinding

class IngredientListAdapter : RecyclerView.Adapter<IngredientListAdapter.ViewHolder>() {

    private val ingredients = mutableListOf<Ingredient>()

    fun updateIngredients(ingredients: List<Ingredient>) {
        this.ingredients.clear()
        this.ingredients.addAll(ingredients)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutIngredientItemBinding.inflate(
            LayoutInflater.from(
                parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }

    override fun getItemCount() = ingredients.count()

    class ViewHolder(
        private val binding: LayoutIngredientItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredient: Ingredient) = with(binding) {
            image.load(url = ingredient.image)
            name.text = ingredient.name
            portion.text = "${ingredient.amount} ${ingredient.measures.metric.unitShort}"
        }
    }
}