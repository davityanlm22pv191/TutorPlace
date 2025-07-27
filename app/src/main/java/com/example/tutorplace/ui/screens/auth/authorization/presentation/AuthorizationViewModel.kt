package com.example.tutorplace.ui.screens.auth.authorization.presentation

import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationCommand.AuthorizeClicked
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationCommand.EmailChanged
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationCommand.PasswordChanged
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationCommand.PasswordHiddenClicked
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationCommand.RegistrationClicked
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationCommand.RestoreClicked
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationCommand.SupportClicked
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationCommand.YandexClicked
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor() :
	BaseViewModel<AuthorizationCommand, AuthorizationEvent, AuthorizationState>() {

	override fun initialState() = AuthorizationState()

	override fun handleCommand(command: AuthorizationCommand) = when (command) {
		is SupportClicked -> sendEvent(AuthorizationEvent.OnSupport)
		is AuthorizeClicked -> sendEvent(AuthorizationEvent.OnHome)
		is EmailChanged -> setState(AuthorizationReducer.reduce(state.value, command))
		is PasswordHiddenClicked -> setState(AuthorizationReducer.reduce(state.value, command))
		is PasswordChanged -> setState(AuthorizationReducer.reduce(state.value, command))
		is RestoreClicked -> sendEvent(AuthorizationEvent.OnRestorePassword)
		is RegistrationClicked -> sendEvent(AuthorizationEvent.OnHome)
		is YandexClicked -> sendEvent(AuthorizationEvent.OnHome)
	}
}