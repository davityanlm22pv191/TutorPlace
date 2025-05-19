package com.example.tutorplace.ui.screens.auth.authorization.presentation

import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationCommand.EnterClicked
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor() :
	BaseViewModel<AuthorizationCommand, AuthorizationEvent, AuthorizationState>() {

	override fun initialState() = AuthorizationState()

	override fun handleCommand(command: AuthorizationCommand) = when (command) {
		EnterClicked -> sendEvent(AuthorizationEvent.OnHome)
	}
}