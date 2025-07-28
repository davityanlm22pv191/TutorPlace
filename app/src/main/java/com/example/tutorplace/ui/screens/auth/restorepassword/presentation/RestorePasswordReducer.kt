package com.example.tutorplace.ui.screens.auth.restorepassword.presentation

import com.example.tutorplace.ui.base.BaseReducer
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordCommand.EmailIsNotValid
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordCommand.EmailSending
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordCommand.EmailSent
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordCommand.RetrySendTimeUpdated
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordCommand.UpdateEmail

object RestorePasswordReducer : BaseReducer<RestorePasswordState, RestorePasswordCommand> {

	override fun reduce(
		oldState: RestorePasswordState,
		command: RestorePasswordCommand
	): RestorePasswordState = when (command) {
		is UpdateEmail -> reduceEmailChanged(oldState, command)
		is EmailSent -> reduceEmailSent(oldState)
		is EmailSending -> oldState.copy(isLoading = true)
		is EmailIsNotValid -> oldState.copy(isEmailError = true)
		is RetrySendTimeUpdated -> oldState.copy(timerInSeconds = command.seconds)
		else -> oldState
	}

	private fun reduceEmailChanged(
		oldState: RestorePasswordState,
		command: UpdateEmail
	): RestorePasswordState = oldState.copy(
		email = command.enteredEmail.trim(),
		isEmailError = false
	)

	private fun reduceEmailSent(
		oldState: RestorePasswordState
	): RestorePasswordState = oldState.copy(
		isEmailSent = true,
		isLoading = false
	)
}