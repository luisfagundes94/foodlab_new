package com.luisfagundes.feature_recipe.presentation.list

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.luisfagundes.base.BaseFragment
import com.luisfagundes.domain.model.Recipe
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
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getRecipesPagingData().collect { pagingData ->
                    recipeListAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun setupRecipeListRecyclerView() = with(binding.rvRecipeList) {
        scrollToPosition(FIRST_ITEM_POSITION)
        setHasFixedSize(true)
        this.adapter = recipeListAdapter.withLoadStateFooter(
            footer = RecipeListLoadStateAdapter(recipeListAdapter::retry)
        )
    }

    private fun observeState() = lifecycleScope.launch {
        recipeListAdapter.loadStateFlow.collectLatest { loadState ->
            binding.rootContainer.displayedChild = when (loadState.refresh) {
                is LoadState.Loading -> FLIPPER_CHILD_LOADING
                is LoadState.NotLoading -> FLIPPER_CHILD_SUCCESS
                is LoadState.Error -> showError()
            }
        }
    }

    private fun showError(): Int {
        binding.recipeListErrorContainer.btnTryAgain.setOnClickListener {
            recipeListAdapter.refresh()
        }
        return FLIPPER_CHILD_ERROR
    }

    private fun navigateToRecipeDetails(recipeId: Int) {
        val action = RecipeListFragmentDirections.actionToRecipeDetailsFragment(
            recipeId = recipeId
        )
        findNavController().navigateWithDirections(action)
    }

    private companion object {
        const val FIRST_ITEM_POSITION = 0
    }
}