package com.example.tutorplace.ui.screens.auth.authorization.presentation

import com.example.tutorplace.ui.base.BaseReducer
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationCommand.*

object AuthorizationReducer : BaseReducer<AuthorizationState, AuthorizationCommand> {
	override fun reduce(
		oldState: AuthorizationState,
		command: AuthorizationCommand
	): AuthorizationState = when (command) {
		is EmailChanged -> oldState.copy(email = command.enteredEmail, isEmailError = false)
		is PasswordChanged -> oldState.copy(password = command.enteredPassword, isPasswordError = false)
		else -> oldState
	}
}