package com.example.tutorplace.navigation.tabs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.tutorplace.navigation.fortuneWheelFlow
import com.example.tutorplace.ui.common.bottomnavbar.BottomTabBarItem
import com.example.tutorplace.ui.screens.home.HomeScreen

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    composable(BottomTabBarItem.Home.route) { HomeScreen(navController) }
    fortuneWheelFlow(navController)
}