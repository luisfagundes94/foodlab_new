package com.luisfagundes.domain.di

import com.luisfagundes.domain.usecase.GetRecipes
import org.koin.dsl.module

val domainModule = module {
    factory { GetRecipes(get()) }
}