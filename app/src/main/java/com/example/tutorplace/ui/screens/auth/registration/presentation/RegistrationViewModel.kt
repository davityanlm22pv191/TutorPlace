package com.example.tutorplace.ui.screens.auth.registration.presentation

import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationEvent.UI.ConfirmPasswordChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationEvent.UI.EmailChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationEvent.UI.NameChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationEvent.UI.PasswordChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationEvent.UI.PhoneChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationEvent.UI.TelegramChanged
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor() :
	BaseViewModel<RegistrationEvent, RegistrationState, RegistrationEffect>() {

	override fun initialState() = RegistrationState()

	override fun onEvent(event: RegistrationEvent) = when (event) {
		is RegistrationEvent.UI -> when (event) {
			is ConfirmPasswordChanged -> setState(RegistrationReducer.reduce(state.value, event))
			is EmailChanged -> setState(RegistrationReducer.reduce(state.value, event))
			is NameChanged -> setState(RegistrationReducer.reduce(state.value, event))
			is PasswordChanged -> setState(RegistrationReducer.reduce(state.value, event))
			is PhoneChanged -> setState(RegistrationReducer.reduce(state.value, event))
			is TelegramChanged -> setState(RegistrationReducer.reduce(state.value, event))
		}
		is RegistrationEvent.Domain -> Unit
	}

	fun onFirstStepClicked() {
		setState(RegistrationReducer.reduce(state.value, RegistrationEvent.Domain.SwitchToFirstStep))
	}

	fun onSecondStepClicked() {
		setState(RegistrationReducer.reduce(state.value, RegistrationEvent.Domain.SwitchToSecondStep))
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