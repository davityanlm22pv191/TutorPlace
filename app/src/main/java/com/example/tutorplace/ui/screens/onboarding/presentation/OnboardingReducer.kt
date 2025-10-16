package com.example.tutorplace.ui.screens.onboarding.presentation

import com.example.tutorplace.data.onboarding.model.OnboardingInfo
import com.example.tutorplace.domain.model.failure
import com.example.tutorplace.domain.model.loaded
import com.example.tutorplace.domain.model.loading
import com.example.tutorplace.ui.base.BaseReducer
import com.example.tutorplace.ui.common.TextFieldState
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

object OnboardingReducer : BaseReducer<OnboardingState, OnboardingEvent> {

	private val isBackButtonVisible: (Step) -> Boolean
		get() = { step ->
			step !in listOf(Step.CONGRATULATIONS, Step.WELCOME, Step.PROVIDE_DETAILS)
		}

	private val isMainButtonEnabled: (Step) -> Boolean
		get() = { step -> step !in listOf(Step.TELL_US_ABOUT_INTERESTS, Step.HELP_YOU_STAY) }

	override fun reduce(
		oldState: OnboardingState,
		event: OnboardingEvent
	): OnboardingState {
		return when (event) {
			is NextStepClicked -> reduceNextStep(oldState)
			is PreviousStepClicked -> reducePreviousStep(oldState)
			is OnboardingInfoLoading -> oldState.copy(onboardingInfo = oldState.onboardingInfo.loading())
			is OnboardingInfoLoadFail -> reduceOnboardingInfoLoadFail(oldState, event.throwable)
			is OnboardingInfoLoaded -> reduceOnboardingInfoLoaded(oldState, event.onboardingInfo)
			is NameValidError -> reduceNameValidError(oldState)
			is NameValueChanged -> reduceNameValueChanged(oldState, event)
			is PasswordValidError -> reducePasswordValidError(oldState)
			is PasswordValueChanged -> reducePasswordValueChanged(oldState, event)
			is RepeatPasswordValidError -> reduceRepeatPasswordError(oldState)
			is RepeatPasswordValueChanged -> reduceRepeatPasswordValueChanged(oldState, event)
			is SexChosen -> reduceSexChosen(oldState, event)
			is SexError -> reduceSexError(oldState)
		}
	}

	private fun reduceSexError(oldState: OnboardingState): OnboardingState {
		return oldState.copy(isSexError = true)
	}

	private fun reduceSexChosen(
		oldState: OnboardingState,
		event: SexChosen
	): OnboardingState {
		return oldState.copy(sex = event.sex, isSexError = false)
	}

	private fun reduceRepeatPasswordError(oldState: OnboardingState): OnboardingState {
		return oldState.copy(
			repeatedPassword = TextFieldState(oldState.repeatedPassword.value, isError = true)
		)
	}

	private fun reduceRepeatPasswordValueChanged(
		oldState: OnboardingState,
		event: RepeatPasswordValueChanged
	): OnboardingState {
		return oldState.copy(
			repeatedPassword = TextFieldState(event.repeatPassword, isError = false)
		)
	}

	private fun reduceNameValueChanged(
		oldState: OnboardingState,
		event: NameValueChanged,
	): OnboardingState {
		return oldState.copy(
			userName = TextFieldState(event.name, isError = false)
		)
	}

	private fun reduceNameValidError(oldState: OnboardingState): OnboardingState {
		return oldState.copy(userName = TextFieldState(oldState.userName.value, isError = true))
	}

	private fun reducePasswordValueChanged(
		oldState: OnboardingState,
		event: PasswordValueChanged
	): OnboardingState {
		return oldState.copy(
			password = TextFieldState(event.password, isError = false)
		)
	}

	private fun reducePasswordValidError(oldState: OnboardingState): OnboardingState {
		return oldState.copy(password = TextFieldState(oldState.password.value, isError = true))
	}

	private fun reduceNextStep(oldState: OnboardingState): OnboardingState {
		val nextStep = when (oldState.step) {
			Step.CONGRATULATIONS -> Step.WELCOME
			Step.WELCOME -> Step.PROVIDE_DETAILS
			Step.PROVIDE_DETAILS -> Step.MORE_OPPORTUNITIES
			Step.MORE_OPPORTUNITIES -> Step.KNOWLEDGE_FROM_MASTERS
			Step.KNOWLEDGE_FROM_MASTERS -> Step.TELL_US_ABOUT_INTERESTS
			Step.TELL_US_ABOUT_INTERESTS -> Step.HELP_YOU_STAY
			Step.HELP_YOU_STAY -> Step.SPEND_YOUR_TIME_PRODUCTIVELY
			Step.SPEND_YOUR_TIME_PRODUCTIVELY -> TODO() // This is final step
		}
		return oldState.copy(
			step = nextStep,
			isMainButtonEnabled = isMainButtonEnabled(nextStep),
			isBackButtonVisible = isBackButtonVisible(nextStep)
		)
	}

	private fun reducePreviousStep(oldState: OnboardingState): OnboardingState {
		val previousStep = when (oldState.step) {
			Step.CONGRATULATIONS -> TODO()
			Step.WELCOME -> Step.CONGRATULATIONS
			Step.PROVIDE_DETAILS -> Step.WELCOME
			Step.MORE_OPPORTUNITIES -> Step.PROVIDE_DETAILS
			Step.KNOWLEDGE_FROM_MASTERS -> Step.MORE_OPPORTUNITIES
			Step.TELL_US_ABOUT_INTERESTS -> Step.KNOWLEDGE_FROM_MASTERS
			Step.HELP_YOU_STAY -> Step.TELL_US_ABOUT_INTERESTS
			Step.SPEND_YOUR_TIME_PRODUCTIVELY -> Step.HELP_YOU_STAY
		}
		return oldState.copy(
			step = previousStep,
			isMainButtonEnabled = isMainButtonEnabled(previousStep),
			isBackButtonVisible = isBackButtonVisible(previousStep)
		)
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