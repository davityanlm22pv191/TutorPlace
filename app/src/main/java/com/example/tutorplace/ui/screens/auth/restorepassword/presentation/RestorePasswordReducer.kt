package com.example.tutorplace.ui.screens.auth.restorepassword.presentation

import com.example.tutorplace.ui.base.BaseReducer
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordCommand.AuthorizeClicked
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordCommand.BackClicked
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordCommand.EmailChanged
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordCommand.RestoreClicked
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordCommand.RetrySendButtonClicked
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordCommand.RetrySendTimerOver

object RestorePasswordReducer : BaseReducer<RestorePasswordState, RestorePasswordCommand> {
	override fun reduce(
		oldState: RestorePasswordState,
		command: RestorePasswordCommand
	): RestorePasswordState = when (command) {
		is BackClicked -> oldState
		is EmailChanged -> oldState
		is AuthorizeClicked -> oldState
		is RestoreClicked -> oldState
		is RetrySendButtonClicked -> oldState
		is RetrySendTimerOver -> oldState
	}
}