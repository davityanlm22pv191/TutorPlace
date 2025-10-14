package com.example.tutorplace.ui.screens.onboarding.presentation

import com.example.tutorplace.data.onboarding.model.OnboardingInfo
import com.example.tutorplace.ui.base.BaseEvent

sealed interface OnboardingEvent : BaseEvent {
	data object NextStepClicked : OnboardingEvent
	data object PreviousStepClicked : OnboardingEvent

	data class OnboardingInfoLoaded(val onboardingInfo: OnboardingInfo) : OnboardingEvent
	data class OnboardingInfoLoadFail(val throwable: Throwable): OnboardingEvent
}