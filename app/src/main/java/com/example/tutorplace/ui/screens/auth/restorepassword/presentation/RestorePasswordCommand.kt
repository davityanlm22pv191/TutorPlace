package com.example.tutorplace.ui.screens.auth.restorepassword.presentation

import com.example.tutorplace.ui.base.BaseCommand

sealed interface RestorePasswordCommand : BaseCommand {
	object BackClicked : RestorePasswordCommand
	data class UpdateEmail(val enteredEmail: String) : RestorePasswordCommand
	object EmailIsNotValid : RestorePasswordCommand
	object EmailErrorSending: RestorePasswordCommand
	object EmailSending: RestorePasswordCommand
	object EmailSent: RestorePasswordCommand
	object RestoreClicked : RestorePasswordCommand
	object AuthorizeClicked : RestorePasswordCommand
	data class RetrySendTimeUpdated(val seconds: Int) : RestorePasswordCommand
	object RetrySendButtonClicked : RestorePasswordCommand
}