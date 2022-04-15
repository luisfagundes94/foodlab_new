package com.luisfagundes.feature_recipe.presentation.details

import android.text.method.LinkMovementMethod
import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.luisfagundes.base.BaseFragment
import com.luisfagundes.domain.model.Recipe
import com.luisfagundes.extensions.load
import com.luisfagundes.feature_recipe.R
import com.luisfagundes.feature_recipe.databinding.FragmentRecipeDetailsBinding
import com.luisfagundes.feature_recipe.model.RecipeDetailsState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeDetailsFragment : BaseFragment<FragmentRecipeDetailsBinding>() {

    private val viewModel: RecipeDetailsViewModel by viewModel()
    private val args: RecipeDetailsFragmentArgs by navArgs()
    private val ingredientAdapter by inject<IngredientListAdapter>()

    override fun onBind() = FragmentRecipeDetailsBinding.inflate(layoutInflater)

    override fun FragmentRecipeDetailsBinding.onViewCreated() {
        setupViews()
        observeUiStatus()

        viewModel.fetchRecipeDetails(args.recipeId)
    }

    private fun setupViews() {
        setupRecyclerView()
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

    private fun observeUiStatus() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            binding.viewFlipperContainer.displayedChild = when (state) {
                is RecipeDetailsState.Loading -> showLoading()
                is RecipeDetailsState.Success -> showSuccess(state.data)
                is RecipeDetailsState.Error -> showError()
            }
        }
    }

    private fun showSuccess(recipe: Recipe): Int = with(binding) {
        imgRecipe.load(recipe.image)
        tvTitle.text = recipe.title
        tvDescription.text = HtmlCompat.fromHtml(recipe.summary, HtmlCompat.FROM_HTML_MODE_LEGACY)
        tvDescription.movementMethod = LinkMovementMethod.getInstance()
        tvReadyInMinutes.text = getReadyInMinutesFormattedText(recipe)
        tvServe.text = getServeFormattedText(recipe)
        tvIngredientsSize.text = getIngredientCountFormattedText(recipe)
        ingredientAdapter.updateIngredients(recipe.ingredients)

        return FLIPPER_CHILD_SUCCESS
    }

    private fun getReadyInMinutesFormattedText(recipe: Recipe) =
        "${recipe.readyInMinutes} ${getString(R.string.minutes)}"

    private fun getServeFormattedText(recipe: Recipe) =
        "${recipe.servings} ${getString(R.string.servings)}"

    private fun getIngredientCountFormattedText(recipe: Recipe) =
        "${recipe.ingredients.count()} ${getString(R.string.items)}"

    private fun showError(): Int {
        binding.recipeDetailsErrorContainer.btnTryAgain.setOnClickListener {
            viewModel.fetchRecipeDetails(args.recipeId)
        }
        return FLIPPER_CHILD_ERROR
    }
}