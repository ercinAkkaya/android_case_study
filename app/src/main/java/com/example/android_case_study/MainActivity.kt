package com.example.android_case_study

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setupWithNavController(navController)

        lifecycleScope.launchWhenStarted {
            mainViewModel.cartItemCount.collect { count ->
                setCartBadge(bottomNavigationView, count)
            }
        }

        lifecycleScope.launchWhenStarted {
            mainViewModel.favoriteItemCount.collect { count ->
                setFavoriteBadge(bottomNavigationView, count)
            }
        }

        // burda backstackte sadece anasayfanın kalmasını sağladık
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_basket -> {
                    navController.navigate(R.id.cartFragment, null, navOptions {
                        popUpTo(R.id.cartFragment) { inclusive = true }
                        launchSingleTop = true
                    })
                    true
                }
                R.id.nav_home -> {
                    navController.navigate(R.id.productListFragment, null, navOptions {
                        popUpTo(R.id.productListFragment) { inclusive = true }
                        launchSingleTop = true
                    })
                    true
                }
                R.id.nav_profile -> {
                    navController.navigate(R.id.profileFragment, null, navOptions {
                        popUpTo(R.id.profileFragment) { inclusive = true }
                        launchSingleTop = true
                    })
                    true
                }
                R.id.nav_star -> {
                    navController.navigate(R.id.favoriteFragment, null, navOptions {
                        popUpTo(R.id.favoriteFragment) { inclusive = true }
                        launchSingleTop = true
                    })
                    true
                }
                else -> false
            }
        }
    }

    private fun setCartBadge(bottomNavigationView: BottomNavigationView, count: Int) {
        val badge = bottomNavigationView.getOrCreateBadge(R.id.nav_basket)
        badge.isVisible = count > 0
        badge.number = count
    }

    private fun setFavoriteBadge(bottomNavigationView: BottomNavigationView, count: Int) {
        val badge = bottomNavigationView.getOrCreateBadge(R.id.nav_star)
        badge.isVisible = count > 0
        badge.number = count
    }
}