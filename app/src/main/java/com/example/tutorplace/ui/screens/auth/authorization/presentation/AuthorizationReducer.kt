package com.example.tutorplace.ui.screens.auth.authorization.presentation

import com.example.tutorplace.ui.base.BaseReducer
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationCommand.EmailChanged
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationCommand.PasswordChanged

object AuthorizationReducer : BaseReducer<AuthorizationState, AuthorizationCommand> {

	override fun reduce(
		oldState: AuthorizationState,
		command: AuthorizationCommand
	): AuthorizationState = when (command) {
		is EmailChanged -> reduceEmailChanged(command, oldState)
		is PasswordChanged -> reducePasswordChanged(command, oldState)
		else -> oldState
	}

	private fun reduceEmailChanged(
		command: EmailChanged,
		oldState: AuthorizationState
	) = oldState.copy(email = command.enteredEmail, isEmailError = false)

	private fun reducePasswordChanged(
		command: PasswordChanged,
		oldState: AuthorizationState
	) = oldState.copy(
		password = command.enteredPassword,
		isPasswordError = false
	)
}