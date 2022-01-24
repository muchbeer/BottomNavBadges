package com.muchbeer.bottomnavbadges

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.muchbeer.bottomnavbadges.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController : NavController
    private lateinit var binding: ActivityMainBinding

    private val badgeViewModel : BadgeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_container)
                                                        as NavHostFragment
        // Setup the ActionBar with navController and 3 top level destinations
        navController = navHostFragment.navController

        setSupportActionBar(binding.toolbar)

        binding.bottomNav.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.listFragment, R.id.profileFragment) )

      //  setupActionBarWithNavController(navController, appBarConfiguration)
       binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        homeBudges()
        listBudges()
        profileBudges()
        observeBadgeToRemove()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

     fun homeBudges() {
         badgeViewModel.homeCount.observe(this) { count ->
             badgesReusable(count, R.id.homeFragment)
         }
     }

    fun observeBadgeToRemove() {
        badgeViewModel.buttonClicked.observe(this) { itemSelected ->
            if (itemSelected == "Home") {
                removeBadges(R.id.homeFragment)
            } else if (itemSelected == "List") {
               removeBadges(R.id.listFragment)
            } else if (itemSelected == "Profile") {
               removeBadges(R.id.profileFragment)
            }
        }
    }

     fun listBudges() {
         badgeViewModel.listCount.observe(this) { count ->
             badgesReusable(count, R.id.listFragment)
         }
    }

     fun profileBudges() {
         badgeViewModel.profileCount.observe(this) { count ->
             badgesReusable(count, R.id.profileFragment)
         }
    }

    private fun badgesReusable(count: Int, badgeId : Int) {
        val badge = binding.bottomNav.getOrCreateBadge(badgeId)

        badge.backgroundColor =  ContextCompat.getColor(this, android.R.color.holo_red_light)
        badge.maxCharacterCount = 3
        badge.isVisible = count > 0
        badge.number = count
    }

    private fun removeBadges(badgeId: Int) {
        binding.bottomNav.removeBadge(badgeId)
    }
}