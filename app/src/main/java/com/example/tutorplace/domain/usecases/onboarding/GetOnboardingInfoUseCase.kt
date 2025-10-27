package com.example.tutorplace.domain.usecases.onboarding

import com.example.tutorplace.data.onboarding.OnboardingService
import com.example.tutorplace.data.onboarding.model.OnboardingInfo
import javax.inject.Inject

class GetOnboardingInfoUseCase @Inject constructor(
	private val onboardingService: OnboardingService,
) {
	suspend fun execute(): Result<OnboardingInfo> {
		val response = onboardingService.getOnboardingInfo()
		return if (response.isSuccessful && response.body() != null) {
			Result.success(response.body()!!)
		} else {
			Result.failure(Throwable(response.message()))
		}
	}
}