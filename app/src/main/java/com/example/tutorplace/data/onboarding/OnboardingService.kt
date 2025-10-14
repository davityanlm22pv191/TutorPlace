package com.example.tutorplace.data.onboarding

import com.example.tutorplace.data.onboarding.model.OnboardingBasicInfo
import retrofit2.Response
import retrofit2.http.GET

interface OnboardingService {

	@GET("onboarding")
	suspend fun getOnboardingInfo(): Response<List<OnboardingBasicInfo>>
}