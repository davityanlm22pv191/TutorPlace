package com.example.tutorplace.data.onboarding

import com.example.tutorplace.data.onboarding.model.OnboardingGiftProductResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class OnboardingServiceImpl @Inject constructor() : OnboardingService {

	override suspend fun getGiftProduct(): Result<OnboardingGiftProductResponse> {
		delay(1000L)
		return Result.success(OnboardingGiftProductResponse(productName = "Астрология"))
	}
}