package com.example.tutorplace.ui.screens.onboarding.presentation

import androidx.lifecycle.viewModelScope
import com.example.tutorplace.domain.usecases.onboarding.GetOnboardingInfoUseCase
import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.NameValidError
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.NameValueChanged
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.NextStepClicked
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.OnboardingInfoLoadFail
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.OnboardingInfoLoaded
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.PasswordValidError
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.PasswordValueChanged
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.PreviousStepClicked
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.RepeatPasswordValidError
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.RepeatPasswordValueChanged
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.SexChosen
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.Step
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
	private val getOnboardingInfoUseCase: GetOnboardingInfoUseCase,
) :
	BaseViewModel<OnboardingEvent, OnboardingState, OnboardingEffect>() {

	init {
		loadGiftProductName()
	}

	override fun initialState() = OnboardingState(step = Step.CONGRATULATIONS)

	override fun onEvent(event: OnboardingEvent) = when (event) {
		is NextStepClicked -> checkCurrentStateAndNavigateToNextStep()
		is PreviousStepClicked -> Unit
		is OnboardingInfoLoaded,
		is OnboardingInfoLoadFail -> Unit
		is NameValidError,
		is NameValueChanged,
		is PasswordValidError,
		is PasswordValueChanged,
		is RepeatPasswordValidError,
		is RepeatPasswordValueChanged,
		is SexChosen -> setState(OnboardingReducer.reduce(state.value, event))
	}

	private fun checkCurrentStateAndNavigateToNextStep() {
		val isAllDataValid = when (state.value.step) {
			Step.PROVIDE_DETAILS -> false
			Step.TELL_US_ABOUT_INTERESTS -> false
			Step.HELP_YOU_STAY -> false
			Step.WELCOME,
			Step.MORE_OPPORTUNITIES,
			Step.KNOWLEDGE_FROM_MASTERS,
			Step.SPEND_YOUR_TIME_PRODUCTIVELY,
			Step.CONGRATULATIONS -> true
		}
		if (isAllDataValid) {
			setState(OnboardingReducer.reduce(state.value, NextStepClicked))
		}
	}

	private fun loadGiftProductName() {
		viewModelScope.launch {
			getOnboardingInfoUseCase
				.execute()
				.onSuccess { onboardingInfo ->
					setState(
						OnboardingReducer.reduce(
							state.value,
							OnboardingInfoLoaded(onboardingInfo)
						)
					)
				}
				.onFailure { throwable ->
					setState(
						OnboardingReducer.reduce(
							state.value,
							OnboardingInfoLoadFail(throwable)
						)
					)
				}
		}
	}
}