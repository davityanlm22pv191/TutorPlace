package com.example.tutorplace.ui.screens.onboarding.presentation

import com.example.tutorplace.ui.base.BaseEvent

sealed interface OnboardingEvent : BaseEvent {
	data object NextStepClicked : OnboardingEvent
	data object PreviousStepClicked : OnboardingEvent

	data class ProductNameLoaded(val productName: String) : OnboardingEvent
	data class ProductNameLoadFail(val throwable: Throwable): OnboardingEvent
}