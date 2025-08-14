package com.example.tutorplace.ui.screens.auth.registration.presentation

import com.example.tutorplace.ui.base.BaseCommand

sealed interface RegistrationCommand : BaseCommand {
	data class NameChanged(val enteredName: String) : RegistrationCommand
	data class PhoneChanged(val enteredPhone: String) : RegistrationCommand
	data class TelegramChanged(val enteredTelegram: String) : RegistrationCommand
	data class EmailChanged(val enteredEmail: String) : RegistrationCommand
	data class PasswordChanged(val enteredPassword: String) : RegistrationCommand
	data class ConfirmPasswordChanged(val enteredConfirmPassword: String) : RegistrationCommand

	object OnNextClicked: RegistrationCommand
	object OnRegistrationClicked: RegistrationCommand
	object OnYandexButtonClicked : RegistrationCommand
	object OnOfferClicked: RegistrationCommand
	object OnTermsClicked: RegistrationCommand
	object OnAuthClicked : RegistrationCommand
	object OnFirstStep: RegistrationCommand
}