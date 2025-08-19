package com.example.tutorplace.ui.navigation

sealed class Destinations(open val route: String) {

	sealed class AuthorizationFlow(override val route: String) : Destinations(route) {
		companion object {
			const val FLOW_ROUTE = "authorization_flow"
		}

		object Auth : AuthorizationFlow("auth")

		object RestorePassword : AuthorizationFlow("restore_password")

		object Registration : AuthorizationFlow("registration")

		// TODO ADD HERE OTHER AUTH SCREENS
	}

	object Home : Destinations("home")
}