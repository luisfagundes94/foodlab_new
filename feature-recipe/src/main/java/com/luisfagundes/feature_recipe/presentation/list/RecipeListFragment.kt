package com.luisfagundes.feature_recipe.presentation.list

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.luisfagundes.base.BaseFragment
import com.luisfagundes.extensions.navigateWithDirections
import com.luisfagundes.feature_recipe.databinding.FragmentRecipeListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RecipeListFragment : BaseFragment<FragmentRecipeListBinding>() {

    private val viewModel: RecipeListViewModel by viewModel()
    private val recipeListAdapter by inject<RecipeListAdapter> {
        parametersOf({ recipeId: Int -> navigateToRecipeDetails(recipeId) })
    }

    override fun onBind() = FragmentRecipeListBinding.inflate(layoutInflater)

    override fun FragmentRecipeListBinding.onViewCreated() {
        setupRecipeListRecyclerView()
        observeState()

        lifecycleScope.launch {
            viewModel.getRecipesPagingData().collect { pagingData ->
                recipeListAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupRecipeListRecyclerView() = with(binding.rvRecipeList) {
        setHasFixedSize(true)
        this.adapter = recipeListAdapter
    }

    private fun observeState() = lifecycleScope.launch {
        recipeListAdapter.loadStateFlow.collectLatest { loadState ->
            binding.rootContainer.displayedChild = when (loadState.refresh) {
                is LoadState.Loading -> FLIPPER_CHILD_LOADING
                is LoadState.NotLoading -> FLIPPER_CHILD_SUCCESS
                is LoadState.Error -> FLIPPER_CHILD_ERROR
            }
        }
    }

    private fun navigateToRecipeDetails(recipeId: Int) {
        val action = RecipeListFragmentDirections.actionToRecipeDetailsFragment(
            recipeId = recipeId
        )
        findNavController().navigateWithDirections(action)
    }
}