package feature.entryPoint.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import core.ui.base.BaseActivity
import core.ui.extension.setVisibilityAnimSlide
import dagger.hilt.android.AndroidEntryPoint
import feature.entryPoint.R
import feature.entryPoint.databinding.ActivityDashboardBinding

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding>(ActivityDashboardBinding::inflate) {

    private lateinit var navController: NavController

    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_dashboard) as NavHostFragment
        navController = navHostFragment.navController
        bind.bottomNavigation.itemIconTintList = null
        bind.bottomNavigation.setupWithNavController(navController)
    }

    override fun initListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            with(destination.id) {
                setBottomNavigationVisibleIn(this == getHomeScreen() || this == getProfileScreen())
            }
        }
    }

    private fun setBottomNavigationVisibleIn(state: Boolean) {
        val visibilityState = if (state) View.VISIBLE else View.GONE
        bind.bottomNavigation.setVisibilityAnimSlide(visibilityState)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            getHomeScreen(), getProfileScreen() -> if (doubleBackToExitPressedOnce) finish() else onHandlerStart()
        }
        @Suppress("DEPRECATION") super.onBackPressed()
    }

    private fun onHandlerStart() {
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed({
                                                        doubleBackToExitPressedOnce = false
                                                    }, 2000)
    }

    private fun getHomeScreen() = R.id.homeFragment

    private fun getProfileScreen() = R.id.profileFragment

}