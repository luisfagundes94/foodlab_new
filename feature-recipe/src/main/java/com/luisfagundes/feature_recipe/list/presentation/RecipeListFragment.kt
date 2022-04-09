package com.luisfagundes.feature_recipe.list.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.luisfagundes.feature_recipe.R

class RecipeListFragment : Fragment(R.layout.fragment_recipe_list) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val destination = RecipeListFragmentDirections.actionHomeFragmentToRecipeDetailsFragment()
            findNavController().navigate(destination)
        }
    }
}