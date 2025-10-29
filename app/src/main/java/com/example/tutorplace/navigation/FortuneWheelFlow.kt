package com.example.tutorplace.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.tutorplace.navigation.Destinations.FortuneWheelFlow
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheel.FortuneWheelScreen
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheel.model.FortuneWheelParams
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheelinformation.FortuneWheelInformationScreen

fun NavGraphBuilder.fortuneWheelFlow(navController: NavHostController) {
	navigation(
		startDestination = FortuneWheelFlow.FortuneWheel(
			FortuneWheelParams(
				isShouldShowInformation = false
			)
		).route,
		route = FortuneWheelFlow.FLOW_ROUTE
	) {
		composable(
			route = FortuneWheelFlow.FortuneWheel.DEFAULT_ROUTE,
			arguments = listOf(
				navArgument(name = "isShouldShowInformation") {
					type = NavType.BoolType
					defaultValue = false
					nullable = false
				}
			)
		) {
			val params = FortuneWheelParams(
				isShouldShowInformation = requireNotNull(it.arguments?.getBoolean("isShouldShowInformation"))
			)
			FortuneWheelScreen(navController, params)
		}
		composable(FortuneWheelFlow.FortuneWheelInformation.route) {
			FortuneWheelInformationScreen(navController)
		}
	}
}