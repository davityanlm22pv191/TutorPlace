package com.example.tutorplace.domain.usecases.onboarding

import com.example.tutorplace.data.onboarding.OnboardingService
import com.example.tutorplace.data.onboarding.model.PlatformAccessDataBody
import com.example.tutorplace.data.onboarding.model.PostInterestListBody
import javax.inject.Inject

class PostOnboardingInfoUseCase @Inject constructor(
	private val onboardingService: OnboardingService
) {
	suspend fun postPlatformAccessData(body: PlatformAccessDataBody): Result<Unit> {
		val response = onboardingService.postPlatformAccessData(body)
		return if (response.isSuccessful) {
			Result.success(Unit)
		} else {
			Result.failure(Throwable(response.message()))
		}
	}

	suspend fun postInterests(list: List<Int>): Result<Unit> {
		val response = onboardingService.postInterests(PostInterestListBody(list))
		return if (response.isSuccessful) {
			Result.success(Unit)
		} else {
			Result.failure(Throwable(response.message()))
		}
	}
}