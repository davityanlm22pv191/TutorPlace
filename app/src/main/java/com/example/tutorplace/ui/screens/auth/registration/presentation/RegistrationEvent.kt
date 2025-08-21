package com.example.tutorplace.ui.screens.auth.registration.presentation

import com.example.tutorplace.ui.base.BaseEvent

sealed interface RegistrationEvent : BaseEvent {

	sealed interface UI : RegistrationEvent {
		data class NameChanged(val enteredName: String) : UI
		data class PhoneChanged(val enteredPhone: String) : UI
		data class TelegramChanged(val enteredTelegram: String) : UI
		data class EmailChanged(val enteredEmail: String) : UI
		data class PasswordChanged(val enteredPassword: String) : UI
		data class ConfirmPasswordChanged(val enteredConfirmPassword: String) : UI
		data object RegisterRequested: UI
	}

	sealed interface Domain : RegistrationEvent {
		data object SwitchToFirstStep : Domain
		data object SwitchToSecondStep : Domain
		data object Register: Domain
	}
}