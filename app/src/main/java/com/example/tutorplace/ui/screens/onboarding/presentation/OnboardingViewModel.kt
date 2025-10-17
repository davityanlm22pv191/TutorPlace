package com.example.tutorplace.ui.screens.onboarding.presentation

import androidx.lifecycle.viewModelScope
import com.example.tutorplace.domain.usecases.onboarding.GetOnboardingInfoUseCase
import com.example.tutorplace.domain.usecases.onboarding.PostOnboardingInfoUseCase
import com.example.tutorplace.data.onboarding.model.PlatformAccessDataBody
import com.example.tutorplace.helpers.FormatHelper
import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.NameValidError
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.NameValueChanged
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.NextStepClicked
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.OnboardingInfoLoadFail
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.OnboardingInfoLoaded
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.OnboardingInfoLoading
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.PasswordValidError
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.PasswordValueChanged
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.PreviousStepClicked
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.RepeatPasswordValidError
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.RepeatPasswordValueChanged
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.SexChosen
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.SexError
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.Step
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
	private val getOnboardingInfoUseCase: GetOnboardingInfoUseCase,
	private val postOnboardingInfoUseCase: PostOnboardingInfoUseCase,
) : BaseViewModel<OnboardingEvent, OnboardingState, OnboardingEffect>() {

	// THIS IS MOCK LINK FOR IMAGES
	// https://iili.io/Kk4qtK7.png
	// https://iili.io/Kkri8V2.png
	// https://iili.io/KkriSPS.png

	init {
		loadGiftProductName()
	}

	override fun initialState() = OnboardingState(step = Step.CONGRATULATIONS)

	override fun onEvent(event: OnboardingEvent) = when (event) {
		is NextStepClicked -> checkCurrentStateAndNavigateToNextStep()
		is OnboardingInfoLoaded,
		is SexError,
		is OnboardingInfoLoadFail -> Unit
		is NameValidError,
		is NameValueChanged,
		is PasswordValidError,
		is PasswordValueChanged,
		is RepeatPasswordValidError,
		is RepeatPasswordValueChanged,
		is PreviousStepClicked,
		is OnboardingInfoLoading,
		is OnboardingEvent.InterestSelected,
		is SexChosen -> setState(OnboardingReducer.reduce(state.value, event))
	}

	private fun checkCurrentStateAndNavigateToNextStep() {
		when (state.value.step) {
			Step.PROVIDE_DETAILS -> setState(
				OnboardingReducer.reduce(
					state.value,
					NextStepClicked
				)
			) // TODO processProvideDetailsStep()
			Step.TELL_US_ABOUT_INTERESTS -> processTellUsAboutInterestsStep()
			Step.HELP_YOU_STAY -> false
			Step.WELCOME,
			Step.MORE_OPPORTUNITIES,
			Step.KNOWLEDGE_FROM_MASTERS,
			Step.SPEND_YOUR_TIME_PRODUCTIVELY,
			Step.CONGRATULATIONS -> {
				if (!state.value.onboardingInfo.isLoading) {
					setState(OnboardingReducer.reduce(state.value, NextStepClicked))
				}
			}
		}
	}

	private fun processTellUsAboutInterestsStep() {
		viewModelScope.launch {
			val isAllDataValid = state.value.selectedInterestsIds.isNotEmpty()
			if (isAllDataValid) {
				setState(OnboardingReducer.reduce(state.value, OnboardingInfoLoading))
				postOnboardingInfoUseCase
					.postInterests(state.value.selectedInterestsIds)
					.onSuccess {
						setState(
							OnboardingReducer.reduce(
								state.value,
								OnboardingInfoLoaded(state.value.onboardingInfo.data)
							)
						)
						setState(OnboardingReducer.reduce(state.value, NextStepClicked))
					}
					.onFailure { throwable ->
						setState(
							OnboardingReducer.reduce(state.value, OnboardingInfoLoadFail(throwable))
						)
					}
			}
		}
	}

	private fun processProvideDetailsStep() {
		viewModelScope.launch {
			val isAllDataValid = checkProvideDetailsStep()
			if (isAllDataValid) {
				setState(OnboardingReducer.reduce(state.value, OnboardingInfoLoading))
				postOnboardingInfoUseCase
					.postPlatformAccessData(
						PlatformAccessDataBody(
							userName = state.value.userName.value,
							password = state.value.password.value,
							sex = state.value.sex ?: return@launch
						)
					)
					.onSuccess {
						setState(
							OnboardingReducer.reduce(
								state.value,
								OnboardingInfoLoaded(state.value.onboardingInfo.data)
							)
						)
						setState(OnboardingReducer.reduce(state.value, NextStepClicked))
					}
					.onFailure { throwable ->
						setState(
							OnboardingReducer.reduce(state.value, OnboardingInfoLoadFail(throwable))
						)
					}
			}
		}
	}

	private fun checkProvideDetailsStep(): Boolean = with(state.value) {
		val errorsEvents = buildList {
			if (!FormatHelper.isValidName(userName.value)) add(NameValidError)
			if (!FormatHelper.isValidPassword(password.value)) add(PasswordValidError)
			if (password.value != repeatedPassword.value ||
				!FormatHelper.isValidPassword(repeatedPassword.value)
			) add(RepeatPasswordValidError)
			if (sex == null) add(SexError)
		}
		errorsEvents.forEach { error -> setState(OnboardingReducer.reduce(state.value, error)) }
		return errorsEvents.isEmpty()
	}

	private fun loadGiftProductName() {
		viewModelScope.launch {
			getOnboardingInfoUseCase
				.execute()
				.onSuccess { onboardingInfo ->
					setState(
						OnboardingReducer.reduce(state.value, OnboardingInfoLoaded(onboardingInfo))
					)
				}
				.onFailure { throwable ->
					setState(
						OnboardingReducer.reduce(state.value, OnboardingInfoLoadFail(throwable))
					)
				}
		}
	}
}