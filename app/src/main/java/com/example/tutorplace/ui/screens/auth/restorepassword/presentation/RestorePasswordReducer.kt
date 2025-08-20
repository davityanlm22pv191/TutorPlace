package com.example.tutorplace.ui.screens.auth.restorepassword.presentation

import com.example.tutorplace.ui.base.BaseReducer
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordEvent.*

object RestorePasswordReducer : BaseReducer<RestorePasswordState, RestorePasswordEvent> {

	override fun reduce(
		oldState: RestorePasswordState,
		event: RestorePasswordEvent
	): RestorePasswordState = when (event) {
		is EmailChanged -> reduceEmailChanged(oldState, event)
		is EmailSent -> reduceEmailSent(oldState)
		is EmailSending -> oldState.copy(isLoading = true)
		is EmailIsNotValid -> oldState.copy(isEmailError = true)
		is RetrySendTimeUpdated -> oldState.copy(timerInSeconds = event.seconds)
		is EmailErrorSending -> oldState.copy(isLoading = false, throwable = null)
	}

	private fun reduceEmailChanged(
		oldState: RestorePasswordState,
		event: EmailChanged
	): RestorePasswordState = oldState.copy(
		email = event.enteredEmail.trim(),
		isEmailError = false
	)

	private fun reduceEmailSent(
		oldState: RestorePasswordState
	): RestorePasswordState = oldState.copy(
		isEmailSent = true,
		isLoading = false
	)
}