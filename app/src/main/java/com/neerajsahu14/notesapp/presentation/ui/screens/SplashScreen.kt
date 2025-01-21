package com.neerajsahu14.notesapp.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.neerajsahu14.notesapp.R

@Composable
fun SplashScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Image(
            painter = painterResource(id = R.drawable.tm_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(200.dp)
                .shadow(50.dp, shape = CircleShape)
        )
        android.os.Handler().postDelayed({
            navController.navigate(Navigation.HOME_SCREEN)
        },3000L)
    }
}