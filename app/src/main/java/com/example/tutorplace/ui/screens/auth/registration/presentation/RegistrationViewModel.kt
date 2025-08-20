package com.example.tutorplace.ui.screens.auth.registration.presentation

import com.example.tutorplace.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor() :
	BaseViewModel<RegistrationEvent, RegistrationState, RegistrationEffect>() {

	override fun initialState() = RegistrationState()

	override fun onEvent(event: RegistrationEvent) = when (event) {
		is RegistrationEvent.UI -> setState(RegistrationReducer.reduce(state.value, event))
		is RegistrationEvent.Domain -> Unit
	}

	fun onFirstStepClicked() {
		setState(
			RegistrationReducer.reduce(
				state.value,
				RegistrationEvent.Domain.SwitchToFirstStep
			)
		)
	}

	fun onSecondStepClicked() {
		setState(
			RegistrationReducer.reduce(
				state.value,
				RegistrationEvent.Domain.SwitchToSecondStep
			)
		)
	}

	fun onRegisterClicked() {
		setState(RegistrationReducer.reduce(state.value, RegistrationEvent.Domain.Register))
		if (state.value.firstStep.isNameError) return
		if (state.value.firstStep.isPhoneNumberError) return
		if (state.value.firstStep.isTelegramError) return
		if (state.value.secondStep.isEmailError) return
		if (state.value.secondStep.isPasswordError) return
		if (state.value.secondStep.isConfirmPasswordError) return
		sendEffect(RegistrationEffect.NavigateToHome)
	}

	fun onOfferClicked() = Unit
	fun onTermsClicked() = Unit
	fun onYandexClicked() = Unit
}