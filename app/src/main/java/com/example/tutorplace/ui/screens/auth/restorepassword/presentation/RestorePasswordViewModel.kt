package com.example.tutorplace.ui.screens.auth.restorepassword.presentation

import androidx.activity.compose.ReportDrawn
import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordCommand.AuthorizeClicked
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordCommand.BackClicked
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordCommand.EmailChanged
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordCommand.RestoreClicked
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordCommand.RetrySendButtonClicked
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordCommand.RetrySendTimerOver
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestorePasswordViewModel @Inject constructor() :
	BaseViewModel<RestorePasswordCommand, RestorePasswordEvent, RestorePasswordState>() {

	override fun initialState() = RestorePasswordState()

	override fun handleCommand(command: RestorePasswordCommand) = when (command) {
		is BackClicked, is AuthorizeClicked -> sendEvent(RestorePasswordEvent.OnAuthorization)
		is EmailChanged -> setState(RestorePasswordReducer.reduce(state.value, command))
		is RestoreClicked -> Unit
		is RetrySendButtonClicked -> Unit
		is RetrySendTimerOver -> Unit
	}
}