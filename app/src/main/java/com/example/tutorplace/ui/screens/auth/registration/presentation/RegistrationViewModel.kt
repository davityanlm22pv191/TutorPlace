package com.example.tutorplace.ui.screens.auth.registration.presentation

import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.ConfirmPasswordChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.EmailChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.NameChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.OnFirstStep
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.OnNextClicked
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.OnOfferClicked
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.OnRegistrationClicked
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.OnTermsClicked
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.OnYandexButtonClicked
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.PasswordChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.PhoneChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.TelegramChanged
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor() :
	BaseViewModel<RegistrationCommand, RegistrationEvent, RegistrationState>() {

	override fun initialState() = RegistrationState()

	override fun handleCommand(command: RegistrationCommand) = when (command) {
		is OnRegistrationClicked -> {
			setState(RegistrationReducer.reduce(state.value, command))
			sendRegistrationRequest()
		}
		is NameChanged -> setState(RegistrationReducer.reduce(state.value, command))
		is PhoneChanged -> setState(RegistrationReducer.reduce(state.value, command))
		is TelegramChanged -> setState(RegistrationReducer.reduce(state.value, command))
		is EmailChanged -> setState(RegistrationReducer.reduce(state.value, command))
		is PasswordChanged -> setState(RegistrationReducer.reduce(state.value, command))
		is ConfirmPasswordChanged -> setState(RegistrationReducer.reduce(state.value, command))
		is OnNextClicked -> setState(RegistrationReducer.reduce(state.value, command))
		is OnFirstStep -> setState(RegistrationReducer.reduce(state.value, command))
		is OnOfferClicked,
		is OnTermsClicked,
		is OnYandexButtonClicked -> sendEvent(RegistrationEvent.OnHome)
	}

	private fun sendRegistrationRequest() {
		if (state.value.firstStep.isNameError) return
		if (state.value.firstStep.isPhoneNumberError) return
		if (state.value.firstStep.isTelegramError) return
		if (state.value.secondStep.isEmailError) return
		if (state.value.secondStep.isPasswordError) return
		if (state.value.secondStep.isConfirmPasswordError) return
		sendEvent(RegistrationEvent.OnHome)
	}
}