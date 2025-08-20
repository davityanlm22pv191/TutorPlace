package com.example.tutorplace.ui.screens.auth.authorization.presentation

import com.example.tutorplace.ui.base.BaseReducer
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.EmailChanged
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.PasswordChanged

object AuthorizationReducer : BaseReducer<AuthorizationState, AuthorizationEvent> {

	override fun reduce(
		oldState: AuthorizationState,
		event: AuthorizationEvent
	): AuthorizationState = when (event) {
		is EmailChanged -> reduceEmailChanged(event, oldState)
		is PasswordChanged -> reducePasswordChanged(event, oldState)
	}

	private fun reduceEmailChanged(
		event: EmailChanged,
		oldState: AuthorizationState
	) = oldState.copy(email = event.enteredEmail, isEmailError = false)

	private fun reducePasswordChanged(
		event: PasswordChanged,
		oldState: AuthorizationState
	) = oldState.copy(
		password = event.enteredPassword,
		isPasswordError = false
	)
}