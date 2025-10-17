package com.example.tutorplace.data.onboarding

import com.example.tutorplace.data.onboarding.model.OnboardingInfo
import com.example.tutorplace.data.onboarding.model.PlatformAccessDataBody
import com.example.tutorplace.data.onboarding.model.PostInterestListBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OnboardingService {

	private companion object {
		const val ENDPOINT = "onboarding"
	}

	@GET(ENDPOINT)
	suspend fun getOnboardingInfo(): Response<List<OnboardingInfo>>

	@POST(ENDPOINT)
	suspend fun postPlatformAccessData(@Body body: PlatformAccessDataBody): Response<Unit>

	@POST(ENDPOINT)
	suspend fun postInterests(@Body body: PostInterestListBody): Response<Unit>
}