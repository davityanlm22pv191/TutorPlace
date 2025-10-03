package com.example.tutorplace.ui.navigation

sealed class Destinations(open val route: String) {

	sealed class AuthorizationFlow(override val route: String) : Destinations(route) {
		companion object {
			const val FLOW_ROUTE = "authorization_flow"
		}

		object Auth : AuthorizationFlow("auth")

		object RestorePassword : AuthorizationFlow("restore_password")

		object Registration : AuthorizationFlow("registration")
	}

	data class Home(val params: HomeParams) : Destinations(route = params.toRoute()) {
		companion object {
			private const val ROUTE = "home"
			const val DEFAULT_ROUTE = "$ROUTE?isShouldShowOnboarding={isShouldShowOnboarding}"

			private fun HomeParams.toRoute() =
				"$ROUTE?isShouldShowOnboarding=${isShouldShowOnboarding}"
		}

		data class HomeParams(
			val isShouldShowOnboarding: Boolean = false
		)
	}

	object Onboarding: Destinations("onboarding")
}