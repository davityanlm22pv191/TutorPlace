package com.example.tutorplace.ui.screens.onboarding.presentation

import com.example.tutorplace.data.onboarding.OnboardingService
import com.example.tutorplace.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
	private val onboardingService: OnboardingService,
) :
	BaseViewModel<OnboardingEvent, OnboardingState, OnboardingEffect>() {

	override fun initialState() = OnboardingState.Quizzes()

	override fun onEvent(event: OnboardingEvent) {

	}
}