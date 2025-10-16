package com.example.tutorplace.data.onboarding.model

data class OnboardingInfo(
	val id: String,
	val productName: String,
	val coverUrl: String,
	val coursesCoverUrls: List<String>,
	val mastersCoverUrls: List<String>
) {
	companion object {
		fun empty() = OnboardingInfo(
			id = "",
			productName = "",
			coverUrl = "",
			coursesCoverUrls = emptyList(),
			mastersCoverUrls = emptyList(),
		)
	}
}