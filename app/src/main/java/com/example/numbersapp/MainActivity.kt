package com.example.numbersapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.numbersapp.ui.screens.Home
import com.example.numbersapp.ui.screens.Routes
import com.example.numbersapp.ui.screens.bottomBar.BottomBar
import com.example.numbersapp.ui.screens.favorite.Favorite
import com.example.numbersapp.ui.screens.settings.Settings
import com.example.numbersapp.ui.theme.CustomNumbersAppTheme
import com.example.numbersapp.ui.theme.CustomTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomNumbersAppTheme {

                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomBar(navController) }
                ) { innerPadding ->

                    NavHost(navController = navController, startDestination = Routes.Home.route) {
                        composable(Routes.Home.route) {
                            Home(modifier = Modifier.background(CustomTheme.colors.background).padding(innerPadding).fillMaxSize().padding(top = 32.dp))
                        }
                        composable(Routes.Favorite.route) {
                            Favorite(modifier = Modifier.background(CustomTheme.colors.background).padding(innerPadding).fillMaxSize().padding(top = 32.dp))
                        }
                        composable(Routes.Settings.route) {
                            Settings(modifier = Modifier.background(CustomTheme.colors.background).padding(innerPadding).fillMaxSize().padding(top = 32.dp))
                        }
                    }

                }
            }
        }

    }
}
