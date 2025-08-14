package com.example.tutorplace.ui.screens.auth.registration.presentation

import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor() :
	BaseViewModel<RegistrationCommand, RegistrationEvent, RegistrationState>() {

	override fun initialState() = RegistrationState()

	override fun handleCommand(command: RegistrationCommand) = when (command) {
		is OnAuthClicked -> sendEvent(RegistrationEvent.OnAuth)
		is OnYandexButtonClicked -> sendEvent(RegistrationEvent.OnHome)
		is OnRegistrationClicked -> setState(RegistrationReducer.reduce(state.value, command))
		is NameChanged -> setState(RegistrationReducer.reduce(state.value, command))
		is PhoneChanged -> setState(RegistrationReducer.reduce(state.value, command))
		is TelegramChanged -> setState(RegistrationReducer.reduce(state.value, command))
		is EmailChanged -> setState(RegistrationReducer.reduce(state.value, command))
		is PasswordChanged -> setState(RegistrationReducer.reduce(state.value, command))
		is ConfirmPasswordChanged -> setState(RegistrationReducer.reduce(state.value, command))
		is OnNextClicked -> setState(RegistrationReducer.reduce(state.value, command))
		is OnFirstStep -> setState(RegistrationReducer.reduce(state.value, command))
		else -> Unit
	}
}