package com.luisfagundes.feature_search.di

import com.luisfagundes.feature_search.presentation.SearchViewModel
import com.luisfagundes.feature_search.presentation.adapters.CuisineListAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    factory { CuisineListAdapter() }
    viewModel {
        SearchViewModel(get())
    }
}