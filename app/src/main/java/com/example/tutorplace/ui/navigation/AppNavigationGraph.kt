package com.example.tutorplace.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tutorplace.ui.screens.home.HomeScreen

@Composable
fun AppNavigationGraph(startDestination: String) {
	val navController = rememberNavController()
	NavHost(navController, startDestination = startDestination) {
		AuthorizationFlow(navController)
		composable(Destinations.Home.route) { HomeScreen(navController) }
	}
}