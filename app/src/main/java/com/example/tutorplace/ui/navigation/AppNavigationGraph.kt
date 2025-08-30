package com.example.tutorplace.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tutorplace.ui.screens.home.HomeScreen

@Composable
fun AppNavigationGraph(startDestination: String) {
	val navController = rememberNavController()
	NavHost(navController, startDestination = startDestination) {
		AuthorizationFlow(navController)
		composable(
			Destinations.Home.DEFAULT_ROUTE,
			arguments = listOf(
				navArgument(name = "isShouldShowOnboarding") {
					type = NavType.BoolType
					defaultValue = false
					nullable = false
				}
			)
		) {
			val params = Destinations.Home.HomeParams(
				isShouldShowOnboarding = requireNotNull(it.arguments?.getBoolean("isShouldShowOnboarding"))
			)

			HomeScreen(navController, params)
		}
	}
}