package com.luisfagundes.foodlab

import android.app.Application
import com.luisfagundes.data.network.di.networkModule
import com.luisfagundes.domain.di.domainModule
import com.luisfagundes.feature_recipe.di.recipeModule
import com.luisfagundes.feature_search.di.searchModule
import org.koin.core.context.startKoin
import timber.log.Timber

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        setupTimber()
        setupKoin()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupKoin() {
        startKoin {
            modules(
                recipeModule,
                networkModule,
                domainModule,
                searchModule
            )
        }
    }
}