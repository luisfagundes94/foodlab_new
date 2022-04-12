package com.luisfagundes.feature_recipe.presentation.list

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.luisfagundes.base.BaseFragment
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.extensions.navigateWithDirections
import com.luisfagundes.feature_recipe.R
import com.luisfagundes.feature_recipe.databinding.FragmentRecipeListBinding
import com.luisfagundes.feature_recipe.model.RecipesUiState
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class RecipeListFragment : BaseFragment<FragmentRecipeListBinding>(
    loadingViewId = R.id.recipe_list_loading_container,
    successViewId = R.id.recipe_list_success_container,
    errorViewId = R.id.recipe_list_error_container
) {

    private val viewModel: RecipeListViewModel by viewModel()
    private val recipeListAdapter by inject<RecipeListAdapter> {
        parametersOf({ recipeId: Int -> navigateToRecipeDetails(recipeId) })
    }

    override fun onBind() = FragmentRecipeListBinding.inflate(layoutInflater)

    override fun FragmentRecipeListBinding.onViewCreated() {
        setupRecipeListRecyclerView()
        setupObservers()

        viewModel.getRecipes()
    }

    private fun setupRecipeListRecyclerView() = with(binding.rvRecipeList) {
        val layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        setHasFixedSize(true)
        this.layoutManager = layoutManager
        this.adapter = recipeListAdapter
    }

    private fun setupObservers() {
        viewModel.recipesUiState.observe(viewLifecycleOwner) { recipesUiState ->
            when (recipesUiState) {
                is RecipesUiState.Success -> showRecipes(recipesUiState.recipes)
                is RecipesUiState.Loading -> showLoading()
                is RecipesUiState.Error -> showError()
            }
        }
    }

    private fun showRecipes(recipes: List<Recipe>) {
        super.showSuccess()
        recipeListAdapter.updateRecipes(recipes)
    }

    override fun showError() = with(binding) {
        super.showError()
        recipeListErrorContainer.btnTryAgain.setOnClickListener {
            viewModel.getRecipes()
        }
    }

    private fun navigateToRecipeDetails(recipeId: Int) {
        val action = RecipeListFragmentDirections.actionToRecipeDetailsFragment(
            recipeId = recipeId
        )
        findNavController().navigateWithDirections(action)
    }
}