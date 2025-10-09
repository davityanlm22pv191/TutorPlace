package com.example.tutorplace.ui.screens.onboarding.presentation

import com.example.tutorplace.ui.base.BaseEvent

sealed interface OnboardingEvent : BaseEvent {
	data object NextStepClicked: OnboardingEvent
	data object PreviousStepClicked: OnboardingEvent


}