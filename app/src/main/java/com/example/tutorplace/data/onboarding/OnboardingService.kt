package com.example.tutorplace.data.onboarding

import com.example.tutorplace.data.onboarding.model.OnboardingGiftProductResponse

interface OnboardingService {

	suspend fun getGiftProduct(): Result<OnboardingGiftProductResponse>
}