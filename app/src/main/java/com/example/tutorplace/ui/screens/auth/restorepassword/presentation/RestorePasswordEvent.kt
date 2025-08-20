package com.example.tutorplace.ui.screens.auth.restorepassword.presentation

import com.example.tutorplace.ui.base.BaseEvent

sealed interface RestorePasswordEvent : BaseEvent {
	data class EmailChanged(val enteredEmail: String) : RestorePasswordEvent
	data object EmailIsNotValid : RestorePasswordEvent
	data object EmailErrorSending : RestorePasswordEvent
	data object EmailSending : RestorePasswordEvent
	data object EmailSent : RestorePasswordEvent
	data class RetrySendTimeUpdated(val seconds: Int) : RestorePasswordEvent
}