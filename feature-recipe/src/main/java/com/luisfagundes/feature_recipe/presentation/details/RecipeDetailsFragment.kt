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

    private fun showRecipeDetails(recipe: Recipe) = with(binding) {
        imgRecipe.load(recipe.image)
        tvTitle.text = recipe.title
        tvDescription.text = HtmlCompat.fromHtml(recipe.summary, HtmlCompat.FROM_HTML_MODE_LEGACY)
        tvDescription.movementMethod = LinkMovementMethod.getInstance()
        tvReadyInMinutes.text = getReadyInMinutesFormattedText(recipe)
        tvServe.text = getServeFormattedText(recipe)
        tvIngredientsSize.text = getIngredientCountFormattedText(recipe)
        ingredientAdapter.updateIngredients(recipe.ingredients)
    }

    private fun getReadyInMinutesFormattedText(recipe: Recipe) =
        "${recipe.readyInMinutes} ${getString(R.string.minutes)}"

    private fun getServeFormattedText(recipe: Recipe) =
        "${recipe.servings} ${getString(R.string.servings)}"

    private fun getIngredientCountFormattedText(recipe: Recipe) =
        "${recipe.ingredients.count()} ${getString(R.string.items)}"

}