package com.example.tutorplace.data.onboarding.model

data class OnboardingInfo(
	val id: String,
	val productName: String
) {
	companion object {
		fun empty() = OnboardingInfo(
			id = "",
			productName = ""
		)
	}
}
