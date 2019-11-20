package com.example.bottombarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    var navHost:NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavInit()
    }


    fun bottomNavInit(){
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
         navHost = Navigation.findNavController(this,R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottomNavigationView,navHost!!)
        NavigationUI.setupActionBarWithNavController(this,navHost!!)
    }

    override fun onSupportNavigateUp(): Boolean {

        navHost?.navigateUp()

        return super.onSupportNavigateUp()
    }
}
