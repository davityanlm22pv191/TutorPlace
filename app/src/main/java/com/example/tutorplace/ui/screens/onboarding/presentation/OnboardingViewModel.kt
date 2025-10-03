package com.example.tutorplace.ui.screens.onboarding.presentation

import com.example.tutorplace.ui.base.BaseViewModel
import javax.inject.Inject

class OnboardingViewModel @Inject constructor() :
	BaseViewModel<OnboardingEvent, OnboardingState, OnboardingEffect>() {

	override fun initialState() = OnboardingState()

	override fun onEvent(event: OnboardingEvent) {

	}
}