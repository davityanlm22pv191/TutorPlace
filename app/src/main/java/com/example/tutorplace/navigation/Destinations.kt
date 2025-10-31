package com.example.tutorplace.navigation

import com.example.tutorplace.ui.screens.fortunewheel.fortunewheel.model.FortuneWheelParams
import kotlinx.serialization.Serializable

sealed interface Destinations {
	val route: String

	@Serializable
	sealed class AuthorizationFlow(override val route: String) : Destinations {
		companion object {
			const val FLOW_ROUTE = "authorization_flow"
		}

		@Serializable
		data object Auth : AuthorizationFlow(route = "auth")

		@Serializable
		data object RestorePassword : AuthorizationFlow("restore_password")

		@Serializable
		data object Registration : AuthorizationFlow("registration")
	}

	@Serializable
	sealed class Tabs(override val route: String) : Destinations {
		@Serializable
		data object Catalog : Tabs("catalog")

		@Serializable
		data object MyCourses : Tabs("my_courses")

		@Serializable
		data object Home : Tabs("home")

		@Serializable
		data object Tasks : Tabs("tasks")
	}

	@Serializable
	data class MainScreen(
		val params: MainScreenParams,
		override val route: String = params.toRoute()
	) : Destinations {

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

	@Serializable
	data object Onboarding : Destinations {
		override val route: String = "onboarding"
	}

	@Serializable
	sealed class FortuneWheelFlow(override val route: String) : Destinations {
		companion object {
			const val FLOW_ROUTE = "fortune_wheel_flow"
		}

		@Serializable
		data class FortuneWheel(val params: FortuneWheelParams) :
			FortuneWheelFlow(route = params.toRoute()) {
			companion object {
				private const val ROUTE = "fortune_wheel"
				const val DEFAULT_ROUTE = "$ROUTE?isShouldShowInformation={isShouldShowInformation}"

				private fun FortuneWheelParams.toRoute() =
					"$ROUTE?isShouldShowInformation=${isShouldShowInformation}"
			}
		}

		@Serializable
		data object FortuneWheelInformation : FortuneWheelFlow("fortune_wheel_information")
	}
}