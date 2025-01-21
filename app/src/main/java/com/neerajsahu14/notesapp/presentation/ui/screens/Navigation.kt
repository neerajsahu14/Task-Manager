package com.neerajsahu14.notesapp.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neerajsahu14.notesapp.presentation.ui.viewmodel.MainActivityViewModel

object Navigation {
    val SPLASHSCREEN = "splashScreen"
    val HOME_SCREEN = "home_screen"
}

@Composable
fun OnBoarding(mainViewModel: MainActivityViewModel, modifier: Modifier) {
val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navigation.SPLASHSCREEN){
        composable(Navigation.SPLASHSCREEN){
            Column(modifier = modifier) {
                SplashScreen(navController)
            }
        }
        composable(Navigation.HOME_SCREEN){
            Column(modifier = modifier) {
                HomeScreen(navController, mainViewModel)
            }
        }
    }
}