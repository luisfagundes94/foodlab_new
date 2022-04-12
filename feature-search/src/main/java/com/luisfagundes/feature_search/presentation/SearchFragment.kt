package com.luisfagundes.feature_search.presentation

import android.widget.ImageView
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luisfagundes.base.BaseFragment
import com.luisfagundes.commons_ui.utils.GridSpacingItemDecoration
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.extensions.hideVisibility
import com.luisfagundes.extensions.navigateWithDirections
import com.luisfagundes.extensions.showVisibility
import com.luisfagundes.feature_recipe.NavigationRecipeDirections
import com.luisfagundes.feature_recipe.presentation.list.RecipeListAdapter
import com.luisfagundes.feature_recipe.presentation.list.RecipeListFragmentDirections
import com.luisfagundes.feature_search.R
import com.luisfagundes.feature_search.databinding.FragmentSearchBinding
import com.luisfagundes.feature_search.model.SearchUiState
import com.luisfagundes.feature_search.presentation.adapters.CuisineListAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SearchFragment : BaseFragment<FragmentSearchBinding>(
    successViewId = R.id.search_success_container,
    loadingViewId = R.id.search_loading_container,
    errorViewId = R.id.search_error_container
) {

    private val viewModel: SearchViewModel by viewModel()

    private val cuisineListAdapter by inject<CuisineListAdapter>()
    private val recipeListAdapter by inject<RecipeListAdapter> {
        parametersOf({ recipeId: Int -> navigateToRecipeDetails(recipeId) })
    }

    override fun onBind() = FragmentSearchBinding.inflate(layoutInflater)

    override fun FragmentSearchBinding.onViewCreated() {
        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        setupCuisineListRecyclerView()
        setupFilteredRecipesRecyclerView()
        setupRecipesSearchViewListener()
    }

    private fun setupCuisineListRecyclerView() = with(binding.rvCuisines) {
        val layoutManager = GridLayoutManager(
            context,
            DEFAULT_CUISINE_SPAN_COUNT,
            GridLayoutManager.VERTICAL,
            false
        )

        setHasFixedSize(true)
        setDefaultGridItemDecoration()
        this.layoutManager = layoutManager
        this.adapter = cuisineListAdapter
    }

    private fun RecyclerView.setDefaultGridItemDecoration() {
        this.addItemDecoration(
            GridSpacingItemDecoration(
                DEFAULT_CUISINE_SPAN_COUNT,
                DEFAULT_CUISINE_SPACING,
                false
            )
        )
    }

    private fun setupFilteredRecipesRecyclerView() = with(binding.rvFilteredRecipes) {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        setHasFixedSize(true)
        this.layoutManager = layoutManager
        this.adapter = recipeListAdapter
    }

    private fun setupRecipesSearchViewListener() = with(binding.svRecipes) {
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { showFilteredRecipeList(it) }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean { return false }
        })

        setupSearchViewCloseButtonListener()
    }

    private fun SearchView.setupSearchViewCloseButtonListener() {
        val searchViewCloseButtonId = requireContext().resources.getIdentifier(
            SEARCH_VIEW_CLOSE_BUTTON_DEFAULT_ID,
            null,
            null
        )
        val searchViewCloseButton = findViewById<ImageView>(searchViewCloseButtonId)

        searchViewCloseButton.setOnClickListener {
            setDefaultSearchViewState()
            showCuisines()
        }
    }

    private fun SearchView.setDefaultSearchViewState() {
        setQuery("", false)
        clearFocus()
    }

    private fun setupObservers() {
        viewModel.searchUiState.observe(viewLifecycleOwner) {
            when (it) {
                is SearchUiState.Success -> showRecipes(it.recipes)
                is SearchUiState.Loading -> showLoading()
                is SearchUiState.Error -> showError()
                else -> showError()
            }
        }
    }

    private fun showRecipes(recipes: List<Recipe>) {
        super.showSuccess()
        recipeListAdapter.updateRecipes(recipes)
    }

    private fun showCuisines() = with(binding) {
        recyclerViewTitle.text = getString(R.string.cuisines)
        rvCuisines.showVisibility()
        rvFilteredRecipes.hideVisibility()
    }

    private fun showFilteredRecipeList(query: String) = with(binding) {
        recyclerViewTitle.text = getString(R.string.results)
        rvCuisines.hideVisibility()
        rvFilteredRecipes.showVisibility()
        viewModel.fetchRecipesByQuery(query)
    }

    private fun navigateToRecipeDetails(recipeId: Int) {
        val action = NavigationRecipeDirections.actionToRecipeDetailsFragment(
            recipeId = recipeId
        )
        findNavController().navigateWithDirections(action)
    }

    private companion object {
        const val DEFAULT_CUISINE_SPAN_COUNT = 2
        const val DEFAULT_CUISINE_SPACING = 20
        const val SEARCH_VIEW_CLOSE_BUTTON_DEFAULT_ID = "android:id/search_close_btn"
    }
}