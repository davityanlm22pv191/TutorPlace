package com.example.tutorplace.navigation

import com.example.tutorplace.ui.screens.fortunewheel.fortunewheel.model.FortuneWheelParams
import kotlinx.serialization.Serializable

sealed class Destinations(open val route: String) {

	sealed class AuthorizationFlow(override val route: String) : Destinations(route) {
		companion object {
			const val FLOW_ROUTE = "authorization_flow"
		}

		object Auth : AuthorizationFlow("auth")

		object RestorePassword : AuthorizationFlow("restore_password")

		object Registration : AuthorizationFlow("registration")
	}

	object Catalog : Destinations("catalog")
	object MyCourses : Destinations("my_courses")
	object Home : Destinations("home")
	object Tasks : Destinations("tasks")

    data class MainScreen(val params: MainScreenParams) : Destinations(route = params.toRoute()) {
		companion object {
			private const val ROUTE = "main"
			const val DEFAULT_ROUTE = "$ROUTE?isShouldShowOnboarding={isShouldShowOnboarding}"

			private fun MainScreenParams.toRoute() =
				"$ROUTE?isShouldShowOnboarding=${isShouldShowOnboarding}"
		}

        @Serializable
        data class MainScreenParams(
			val isShouldShowOnboarding: Boolean = false
		)
	}

	object Onboarding : Destinations("onboarding")

	sealed class FortuneWheelFlow(override val route: String) : Destinations(route) {
		companion object {
			const val FLOW_ROUTE = "fortune_wheel_flow"
		}

		data class FortuneWheel(
			val params: FortuneWheelParams
		) : FortuneWheelFlow(route = params.toRoute()) {
			companion object {
				private const val ROUTE = "fortune_wheel"
				const val DEFAULT_ROUTE = "$ROUTE?isShouldShowInformation={isShouldShowInformation}"

				private fun FortuneWheelParams.toRoute() =
					"$ROUTE?isShouldShowInformation=${isShouldShowInformation}"
			}
		}

		object FortuneWheelInformation : FortuneWheelFlow("fortune_wheel_information")
	}
}