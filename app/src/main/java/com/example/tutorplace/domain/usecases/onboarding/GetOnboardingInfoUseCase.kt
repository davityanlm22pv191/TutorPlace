package com.example.tutorplace.domain.usecases.onboarding

import com.example.tutorplace.data.onboarding.OnboardingService
import com.example.tutorplace.data.onboarding.model.OnboardingBasicInfo
import javax.inject.Inject

class GetOnboardingInfoUseCase @Inject constructor(
	private val onboardingService: OnboardingService,
) {
	suspend fun execute(): Result<OnboardingBasicInfo> {
		val response = onboardingService.getOnboardingInfo()
		return if (response.isSuccessful) {
			Result.success(response.body()!!.first())
		} else {
			Result.failure(Throwable(response.message()))
		}
	}
}