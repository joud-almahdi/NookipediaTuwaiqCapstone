package com.example.nookipedia.main

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.akexorcist.localizationactivity.core.LocalizationActivityDelegate
import com.akexorcist.localizationactivity.core.OnLocaleChangedListener
import com.example.nookipedia.R
import com.example.nookipedia.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(),OnLocaleChangedListener {
    private val localizationDelegate = LocalizationActivityDelegate(this)
   private lateinit var binding:ActivityMainBinding
   private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        localizationDelegate.addOnLocaleChangedListener(this)
        localizationDelegate.onCreate()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
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



    public override fun onResume() {
        super.onResume()
        localizationDelegate.onResume(this)
    }

    override fun attachBaseContext(newBase: Context) {
        applyOverrideConfiguration(localizationDelegate.updateConfigurationLocale(newBase))
        super.attachBaseContext(newBase)
    }

    override fun getApplicationContext(): Context {
        return localizationDelegate.getApplicationContext(super.getApplicationContext())
    }

    override fun getResources(): Resources {
        return localizationDelegate.getResources(super.getResources())
    }

    fun setLanguage(language: String?) {
        localizationDelegate.setLanguage(this, language!!)
    }

    fun setLanguage(locale: Locale?) {
        localizationDelegate.setLanguage(this, locale!!)
    }

    val currentLanguage: Locale
        get() = localizationDelegate.getLanguage(this)









    override fun onAfterLocaleChanged() {
        //
    }

    override fun onBeforeLocaleChanged() {
        //
    }


}