package com.app.homecontrol.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.app.homecontrol.navigation.Screen

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    LaunchedEffect(key1 = Unit) {
        navHostController.navigate(Screen.Home.route)
//        navHostController.navigate(Screen.Fan.route)
//        navHostController.navigate(Screen.Bulb.route)
    }
}