package com.luisfagundes.foodlab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.luisfagundes.extensions.showVisibility
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
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                com.luisfagundes.feature_home.R.id.navigation_home,
                com.luisfagundes.feature_favorites.R.id.navigation_favorites,
                com.luisfagundes.feature_pantry.R.id.navigation_pantry
            )
        )

        //setupImmersiveView(navController, bottomNavView)

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)
    }

    private fun setupImmersiveView(
        navController: NavController,
        bottomNav: BottomNavigationView
    ) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                else -> bottomNav.showVisibility()
            }
        }
    }
}