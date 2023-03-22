package com.example.pokemontestapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.pokemontestapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("TEST", "just created main activity")

//        val navController = getRootNavController()
//        onNav
    }

//    private fun getRootNavController(): NavController {
//        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//        return navHost.navController
//    }
}