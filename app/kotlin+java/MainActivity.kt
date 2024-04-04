package com.nazaroi.moviecatalog

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.nazaroi.base.ktx.context
import com.nazaroi.base.ktx.getEnumOrThrow
import com.nazaroi.base.ktx.logI
import com.nazaroi.common.ktx.setBackNavigation
import com.nazaroi.common.screen.Screen
import com.nazaroi.common.screen.routeNoParams
import com.nazaroi.common.shared.SharedEvent
import com.nazaroi.common.shared.SharedViewModel
import com.nazaroi.domain.enums.MovieCategory
import com.nazaroi.moviecatalog.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    private val viewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBottomNavigationView()
        setupDrawerLayout()
        setupToolbar()

    }

    private fun setupDrawerLayout() {

        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        val navOptions = NavOptions.Builder().setEnterAnim(com.nazaroi.base.R.anim.slide_in_right)
            .setExitAnim(com.nazaroi.base.R.anim.slide_out_left)
            .setPopEnterAnim(com.nazaroi.base.R.anim.slide_in_left)
            .setPopExitAnim(com.nazaroi.base.R.anim.slide_out_right).build()

        binding.navView.setNavigationItemSelectedListener {

            binding.drawerLayout.closeDrawer(binding.navView)

            when (it.itemId) {
                R.id.menu_privacy_policy -> {
                    val request = NavDeepLinkRequest.Builder.fromUri(
                        Screen.PrivacyPolicy.routeNoParams().toUri()
                    ).build()
                    navController.navigate(request, navOptions)
                    true
                }

                R.id.menu_about_us -> {
                    val request = NavDeepLinkRequest.Builder.fromUri(
                        Screen.AboutUs.routeNoParams().toUri()
                    ).build()
                    navController.navigate(request, navOptions)
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    private fun setupBottomNavigationView() {
        binding.bottomNav.setupWithNavController(navController)

        binding.bottomNav.setOnItemSelectedListener { item ->

            navController.popBackStack(R.id.search_graph, true)
            navController.popBackStack(R.id.wishlist_graph, true)
            navController.popBackStack(com.nazaroi.feature_menu.R.id.menu_graph, true)

            when (item.itemId) {
                R.id.home_graph -> {
                    logI("Home tab item selected")
                    navController.popBackStack(
                        com.nazaroi.feature_home.R.id.home_destination, false
                    )
                    true
                }

                R.id.search_graph -> {
                    logI("Search tab item selected")
                    navController.navigate(R.id.search_graph)
                    true
                }

                R.id.wishlist_graph -> {
                    logI("Wishlist tab item selected")
                    navController.navigate(R.id.wishlist_graph)
                    true
                }

                else -> false
            }
        }
    }

    private fun setupToolbar() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    binding.setupToolbar(navController, state.currentScreen, state.toolbarData) {
                        viewModel.sendEvent(SharedEvent.RightToolbarIconClick)
                    }
                }
            }
        }
    }
}