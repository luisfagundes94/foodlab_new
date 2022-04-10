package com.luisfagundes.feature_recipe.di

import com.luisfagundes.feature_recipe.presentation.list.RecipeListAdapter
import com.luisfagundes.feature_recipe.presentation.list.RecipeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val recipeModule = module {

    factory { RecipeListAdapter() }

    viewModel { RecipeListViewModel(get()) }
}