package com.example.numbersapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.numbersapp.ui.screens.home.Home
import com.example.numbersapp.ui.screens.Routes
import com.example.numbersapp.ui.screens.aboutApp.AboutApp
import com.example.numbersapp.ui.screens.bottomBar.BottomBar
import com.example.numbersapp.ui.screens.favorite.Favorite
import com.example.numbersapp.ui.screens.settings.Settings
import com.example.numbersapp.ui.theme.CustomNumbersAppTheme
import com.example.numbersapp.ui.theme.CustomTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        enableEdgeToEdge()
        setContent {
            CustomNumbersAppTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomBar(navController) }
                ) { innerPadding ->
                    NavHost(navController = navController, startDestination = Routes.Home.route) {
                        // Home Screen
                        composable(
                            route = Routes.Home.route,
                            enterTransition = {
                                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))
                            },
                            exitTransition = {
                                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))
                            },
                            popEnterTransition = {
                                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))
                            },
                            popExitTransition = {
                                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))
                            }
                        ) {
                            Home(modifier = Modifier.background(CustomTheme.colors.background).padding(innerPadding).fillMaxSize().padding(top = 32.dp))
                        }

                        // Favorite Screen
                        composable(
                            route = Routes.Favorite.route,
                            enterTransition = {
                                if (initialState.destination.route == Routes.Settings.route) {
                                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))
                                } else {
                                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))
                                }
                            },
                            exitTransition = {
                                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))
                            },
                            popEnterTransition = {
                                if (initialState.destination.route == Routes.Settings.route) {
                                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))
                                } else {
                                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))
                                }
                            },
                            popExitTransition = {
                                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))
                            }
                        ) {
                            Favorite(modifier = Modifier.background(CustomTheme.colors.background).padding(innerPadding).fillMaxSize().padding(top = 32.dp))
                        }

                        // Settings Screen
                        composable(
                            route = Routes.Settings.route,
                            enterTransition = {
                                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))
                            },
                            exitTransition = {
                                if (targetState.destination.route == Routes.Favorite.route) {
                                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))
                                } else {
                                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))
                                }
                            },
                            popEnterTransition = {
                                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))
                            },
                            popExitTransition = {
                                if (targetState.destination.route == Routes.Favorite.route) {
                                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))
                                } else {
                                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))
                                }
                            }
                        ) {
                            Settings(navController = navController, modifier = Modifier.background(CustomTheme.colors.background).padding(innerPadding).fillMaxSize().padding(top = 32.dp))
                        }

                        // AboutApp
                        composable(
                            route = Routes.AboutApp.route,
                            enterTransition = {
                                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))
                            },
                            exitTransition = {
                                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))
                            },
                            popEnterTransition = {
                                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))
                            },
                            popExitTransition = {
                                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))
                            }
                        ) {
                            AboutApp(navController = navController, modifier = Modifier.background(CustomTheme.colors.background).padding(innerPadding).fillMaxSize().padding(top = 32.dp))
                        }
                    }
                }
            }
        }

    }
}
