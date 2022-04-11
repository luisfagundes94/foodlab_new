package com.luisfagundes.feature_recipe.presentation.details

import android.text.method.LinkMovementMethod
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.luisfagundes.base.BaseFragment
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.extensions.load
import com.luisfagundes.extensions.showVisibility
import com.luisfagundes.feature_recipe.R
import com.luisfagundes.feature_recipe.databinding.FragmentRecipeDetailsBinding
import com.luisfagundes.feature_recipe.model.RecipeDetailsState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeDetailsFragment : BaseFragment<FragmentRecipeDetailsBinding>(
    successViewId = R.id.recipe_details_success_container,
    loadingViewId = R.id.recipe_details_loading_container,
    errorViewId = R.id.recipe_details_error_container
) {

    private val viewModel: RecipeDetailsViewModel by viewModel()
    private val args: RecipeDetailsFragmentArgs by navArgs()
    private val ingredientAdapter by inject<IngredientListAdapter>()

    override fun onBind() = FragmentRecipeDetailsBinding.inflate(layoutInflater)

    override fun FragmentRecipeDetailsBinding.onViewCreated() {
        setupViews()
        setupObservers()

        viewModel.fetchRecipeDetails(args.recipeId)
    }

    private fun setupViews() {
        setupRecyclerView()
        setupUpActionButton()
    }

    private fun setupRecyclerView() = with(binding.rvIngredients) {
        val layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )

        setHasFixedSize(true)
        this.layoutManager = layoutManager
        this.adapter = this@RecipeDetailsFragment.ingredientAdapter
    }

    private fun setupObservers() {
        viewModel.recipe.observe(viewLifecycleOwner) {
            when (it) {
                is RecipeDetailsState.Success -> showRecipeDetails(it.data)
                is RecipeDetailsState.Loading -> showLoading()
                is RecipeDetailsState.Error -> showError()
                else -> showError()
            }
        }
    }

    override fun showError() = with(binding) {
        super.showError()

        recipeDetailsErrorContainer.btnTryAgain.setOnClickListener {
            viewModel.fetchRecipeDetails(args.recipeId)
        }
    }

    private fun showRecipeDetails(recipe: Recipe) = with(binding) {
        imgRecipe.load(recipe.image)
        tvRecipeTitle.text = recipe.title
        tvRecipeDescription.text = HtmlCompat.fromHtml(recipe.summary, HtmlCompat.FROM_HTML_MODE_LEGACY)
        tvRecipeDescription.movementMethod = LinkMovementMethod.getInstance()
        tvDuration.text = "${recipe.readyInMinutes} min"
        tvServe.text = "${recipe.servings} serve"
        tvIngredientsSize.text = "${recipe.ingredients.count()} items"
        ingredientAdapter.updateIngredients(recipe.ingredients)
    }

}