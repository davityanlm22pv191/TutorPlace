package com.example.tutorplace.ui.screens.onboarding.presentation

import com.example.tutorplace.data.onboarding.model.OnboardingInfo
import com.example.tutorplace.domain.model.failure
import com.example.tutorplace.domain.model.loaded
import com.example.tutorplace.ui.base.BaseReducer

object OnboardingReducer : BaseReducer<OnboardingState, OnboardingEvent> {

	override fun reduce(
		oldState: OnboardingState,
		event: OnboardingEvent
	): OnboardingState {
		return when (event) {
			is OnboardingEvent.NextStepClicked -> reduceNextStep(oldState)
			is OnboardingEvent.PreviousStepClicked -> reducePreviousStep(oldState)
			is OnboardingEvent.OnboardingInfoLoadFail -> reduceOnboardingInfoLoadFail(
				oldState,
				event.throwable
			)
			is OnboardingEvent.OnboardingInfoLoaded -> reduceOnboardingInfoLoaded(
				oldState,
				event.onboardingInfo
			)
		}
	}

	private fun reduceNextStep(oldState: OnboardingState): OnboardingState {
		return oldState
	}

	private fun reducePreviousStep(oldState: OnboardingState): OnboardingState {
		return oldState
	}

	private fun reduceOnboardingInfoLoaded(
		oldState: OnboardingState,
		onboardingInfo: OnboardingInfo
	): OnboardingState = oldState.copy(
		onboardingInfo = oldState.onboardingInfo.loaded(onboardingInfo),
		isMainButtonEnabled = true
	)

	private fun reduceOnboardingInfoLoadFail(
		oldState: OnboardingState,
		throwable: Throwable
	): OnboardingState = oldState.copy(
		onboardingInfo = oldState.onboardingInfo.failure(throwable),
		isMainButtonEnabled = true
	)
}