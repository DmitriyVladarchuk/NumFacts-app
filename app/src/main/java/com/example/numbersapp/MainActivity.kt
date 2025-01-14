package com.example.numbersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.numbersapp.ui.screens.Home
import com.example.numbersapp.ui.screens.Routes
import com.example.numbersapp.ui.screens.bottomBar.BottomBar
import com.example.numbersapp.ui.screens.like.Favorite
import com.example.numbersapp.ui.screens.settings.Settings
import com.example.numbersapp.ui.theme.NumbersAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NumbersAppTheme {

                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomBar(navController) }
                ) { innerPadding ->

                    NavHost(navController = navController, startDestination = Routes.Home.route) {
                        composable(Routes.Home.route) {
                            Home(modifier = Modifier.padding(innerPadding))
                        }
                        composable(Routes.Favorite.route) {
                            Favorite(modifier = Modifier.padding(innerPadding))
                        }
                        composable(Routes.Settings.route) {
                            Settings(modifier = Modifier.padding(innerPadding))
                        }
                    }

                }
            }
        }

    }
}
