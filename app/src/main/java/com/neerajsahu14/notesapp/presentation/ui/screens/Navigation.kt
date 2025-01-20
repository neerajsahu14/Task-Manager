package com.neerajsahu14.notesapp.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object Navigation {
    val SPLASHSCREEN = "splashScreen"
    val HOME_SCREEN = "home_screen"
}

@Composable
fun OnBoarding() {
val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navigation.SPLASHSCREEN){
        composable(Navigation.SPLASHSCREEN){

        }
        composable(Navigation.HOME_SCREEN){

        }
    }
}