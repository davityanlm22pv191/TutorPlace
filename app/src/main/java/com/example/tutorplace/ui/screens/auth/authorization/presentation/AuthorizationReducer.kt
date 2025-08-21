package com.example.tutorplace.ui.screens.auth.authorization.presentation

import com.example.tutorplace.helpers.FormatHelper
import com.example.tutorplace.ui.base.BaseReducer
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.CheckEnteredValues
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.EmailChanged
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.EnterToProfileRequested
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.PasswordChanged

object AuthorizationReducer : BaseReducer<AuthorizationState, AuthorizationEvent> {

	override fun reduce(
		oldState: AuthorizationState,
		event: AuthorizationEvent
	): AuthorizationState = when (event) {
		is EmailChanged -> reduceEmailChanged(event, oldState)
		is PasswordChanged -> reducePasswordChanged(event, oldState)
		is CheckEnteredValues -> reduceCheckEnteredValues(oldState)
		is EnterToProfileRequested -> oldState.copy(isLoading = true)
	}

	private fun reduceEmailChanged(
		event: EmailChanged,
		oldState: AuthorizationState
	) = oldState.copy(
		email = event.enteredEmail,
		isEmailError = false
	)

	private fun reducePasswordChanged(
		event: PasswordChanged,
		oldState: AuthorizationState
	) = oldState.copy(
		password = event.enteredPassword,
		isPasswordError = false
	)

	private fun reduceCheckEnteredValues(
		oldState: AuthorizationState
	) = oldState.copy(
		isEmailError = !FormatHelper.isValidEmail(oldState.email),
		isPasswordError = !FormatHelper.isValidPassword(oldState.password)
	)
}