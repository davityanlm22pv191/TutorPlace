package com.example.tutorplace.domain.usecases.onboarding

import com.example.tutorplace.data.onboarding.OnboardingService
import com.example.tutorplace.domain.usecases.onboarding.model.PlatformAccessDataBody
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
}