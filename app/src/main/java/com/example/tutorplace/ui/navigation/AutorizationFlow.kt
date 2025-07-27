package com.example.tutorplace.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tutorplace.ui.navigation.Destinations.AuthorizationFlow
import com.example.tutorplace.ui.screens.auth.authorization.AuthorizationScreen
import com.example.tutorplace.ui.screens.auth.restorepassword.RestorePasswordScreen

fun NavGraphBuilder.AutorizationFlow(navController: NavController) {
	navigation(
		startDestination = AuthorizationFlow.Auth.route,
		route = AuthorizationFlow.FLOW_ROUTE
	) {
		composable(AuthorizationFlow.Auth.route) { AuthorizationScreen(navController) }
		composable(AuthorizationFlow.RestorePassword.route) { RestorePasswordScreen(navController) }
	}
}