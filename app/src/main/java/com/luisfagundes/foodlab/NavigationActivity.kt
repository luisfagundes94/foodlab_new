package com.luisfagundes.foodlab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.luisfagundes.foodlab.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewBinding()
        setupBottomNavigation()
    }

    private fun setupViewBinding() {
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupBottomNavigation() {
        val bottomNavView = binding.bottomNavItems
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment_container
        ) as NavHostFragment
        val navController = navHostFragment.navController

        bottomNavView.setupWithNavController(navController)
        binding.toolbar.setupWithNavController(navController, getAppBarConfig())
        setupAppBarBackButton(navController)
    }

    private fun getAppBarConfig() = AppBarConfiguration(
        setOf(
            com.luisfagundes.feature_recipe.R.id.recipeListFragment,
            com.luisfagundes.feature_search.R.id.searchFragment,
            com.luisfagundes.feature_favorites.R.id.favoritesFragment,
            com.luisfagundes.feature_pantry.R.id.pantryFragment
        )
    )

    private fun setupAppBarBackButton(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val topLevelDestinations = getAppBarConfig().topLevelDestinations
            val isTopLevelDestination = topLevelDestinations.contains(destination.id)
            
            if (!isTopLevelDestination) binding.toolbar.setNavigationIcon(
                R.drawable.ic_baseline_arrow_back_24
            )
        }
    }
}