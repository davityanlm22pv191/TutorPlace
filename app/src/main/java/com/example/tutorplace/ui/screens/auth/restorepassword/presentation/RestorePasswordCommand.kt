package com.example.tutorplace.ui.screens.auth.restorepassword.presentation

import com.example.tutorplace.ui.base.BaseCommand

sealed interface RestorePasswordCommand : BaseCommand {
	object BackClicked : RestorePasswordCommand
	data class EmailChanged(val enteredEmail: String) : RestorePasswordCommand
	object RestoreClicked : RestorePasswordCommand
	object AuthorizeClicked : RestorePasswordCommand
	object RetrySendTimerOver : RestorePasswordCommand
	object RetrySendButtonClicked : RestorePasswordCommand
}