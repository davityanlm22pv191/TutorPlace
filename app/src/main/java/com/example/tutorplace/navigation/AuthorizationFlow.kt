package com.example.tutorplace.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tutorplace.navigation.Destinations.AuthorizationFlow
import com.example.tutorplace.ui.screens.auth.authorization.AuthorizationScreen
import com.example.tutorplace.ui.screens.auth.registration.RegistrationScreen
import com.example.tutorplace.ui.screens.auth.restorepassword.RestorePasswordScreen

fun NavGraphBuilder.authorizationFlow(navController: NavHostController) {
	navigation(
		startDestination = AuthorizationFlow.Auth.route,
		route = AuthorizationFlow.FLOW_ROUTE
	) {
		composable(AuthorizationFlow.Auth.route) { AuthorizationScreen(navController) }
		composable(AuthorizationFlow.RestorePassword.route) { RestorePasswordScreen(navController) }
		composable(AuthorizationFlow.Registration.route) { RegistrationScreen(navController) }
	}
}