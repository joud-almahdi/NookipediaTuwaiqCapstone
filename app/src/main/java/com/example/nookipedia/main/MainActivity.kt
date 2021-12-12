package com.example.nookipedia.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.nookipedia.R
import com.example.nookipedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   private lateinit var binding:ActivityMainBinding
   private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navigationhostfragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController=navigationhostfragment.navController
        setupActionBarWithNavController(navController)
        NavigationUI.setupWithNavController(binding.animalnavview,navController)


        //sss
        //mbmbmb
    }

    //to have  the back button  work
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}