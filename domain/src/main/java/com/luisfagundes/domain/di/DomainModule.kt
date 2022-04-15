package com.luisfagundes.domain.di

import com.luisfagundes.domain.usecase.GetRecipes
import com.luisfagundes.domain.usecase.SearchRecipes
import org.koin.dsl.module

val domainModule = module {
    factory { GetRecipes(get()) }
    factory { SearchRecipes(get()) }
    //factory { GetRecipeDetails(get()) }
}