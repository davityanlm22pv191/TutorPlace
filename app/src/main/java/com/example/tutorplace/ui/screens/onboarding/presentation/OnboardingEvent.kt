package com.example.tutorplace.ui.screens.onboarding.presentation

import com.example.tutorplace.data.onboarding.model.OnboardingInfo
import com.example.tutorplace.domain.model.Sex
import com.example.tutorplace.ui.base.BaseEvent

sealed interface OnboardingEvent : BaseEvent {
	data object NextStepClicked : OnboardingEvent
	data object PreviousStepClicked : OnboardingEvent

	data class NameValueChanged(val name: String) : OnboardingEvent
	data object NameValidError : OnboardingEvent
	data class PasswordValueChanged(val password: String): OnboardingEvent
	data object PasswordValidError : OnboardingEvent
	data class RepeatPasswordValueChanged(val repeatPassword: String) : OnboardingEvent
	data object RepeatPasswordValidError : OnboardingEvent
	data class SexChosen(val sex: Sex) : OnboardingEvent

	data class OnboardingInfoLoaded(val onboardingInfo: OnboardingInfo) : OnboardingEvent
	data class OnboardingInfoLoadFail(val throwable: Throwable) : OnboardingEvent
}