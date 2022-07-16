package com.luisfagundes.di

import com.luisfagundes.usecases.GetRecipeDetails
import com.luisfagundes.usecases.GetRecipes
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetRecipes(get())  }
    factory { GetRecipeDetails(get()) }
}