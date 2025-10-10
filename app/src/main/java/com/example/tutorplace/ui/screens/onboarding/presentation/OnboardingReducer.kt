package com.example.tutorplace.ui.screens.onboarding.presentation

import com.example.tutorplace.domain.model.failure
import com.example.tutorplace.domain.model.loaded
import com.example.tutorplace.ui.base.BaseReducer

object OnboardingReducer : BaseReducer<OnboardingState, OnboardingEvent> {

	override fun reduce(
		oldState: OnboardingState,
		event: OnboardingEvent
	): OnboardingState {
		return when (event) {
			is OnboardingEvent.NextStepClicked -> oldState
			is OnboardingEvent.PreviousStepClicked -> oldState
			is OnboardingEvent.ProductNameLoadFail -> reduceProductNameLoadFail(
				oldState as OnboardingState.Quizzes,
				event.throwable
			)
			is OnboardingEvent.ProductNameLoaded -> reduceProductNameLoaded(
				oldState as OnboardingState.Quizzes,
				event.productName
			)
		}
	}

	private fun reduceProductNameLoaded(
		oldState: OnboardingState.Quizzes,
		productName: String
	): OnboardingState = oldState.copy(productName = oldState.productName.loaded(productName))

	private fun reduceProductNameLoadFail(
		oldState: OnboardingState.Quizzes,
		throwable: Throwable
	): OnboardingState = oldState.copy(productName = oldState.productName.failure(throwable))
}