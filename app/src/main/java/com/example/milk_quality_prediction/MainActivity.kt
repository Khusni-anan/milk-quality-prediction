package com.example.milk_quality_prediction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.milk_quality_prediction.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Setup with NavController
        binding.bottomNavigation.setupWithNavController(navController)

        // Override behavior for Home button to clear backstack
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    val options = NavOptions.Builder()
                        .setPopUpTo(navController.graph.startDestinationId, inclusive = false)
                        .build()
                    navController.navigate(R.id.homeFragment, null, options)
                    true
                }
                R.id.datasetFragment -> {
                    navController.navigate(R.id.datasetFragment)
                    true
                }
                R.id.fiturFragment -> {
                    navController.navigate(R.id.fiturFragment)
                    true
                }
                R.id.modelFragment -> {
                    navController.navigate(R.id.modelFragment)
                    true
                }
                R.id.simulasiFragment -> {
                    navController.navigate(R.id.simulasiFragment)
                    true
                }
                R.id.infoFragment -> {
                    navController.navigate(R.id.infoFragment)
                    true
                }
                else -> false
            }
        }
    }
}
