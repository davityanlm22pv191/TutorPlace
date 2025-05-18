package com.example.tutorplace.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigationGraph(startDestination: String) {
	val navController = rememberNavController()
	NavHost(navController, startDestination = startDestination) {
		AutorizationFlow(navController)
	}
}