package com.example.tutorplace.ui.screens.auth.authorization.presentation

import com.example.tutorplace.ui.base.BaseCommand

sealed interface AuthorizationCommand : BaseCommand {
	object AuthorizeClicked : AuthorizationCommand
	data class EmailChanged(val enteredEmail: String) : AuthorizationCommand
	data class PasswordChanged(val enteredPassword: String) : AuthorizationCommand
	object PasswordHiddenClicked : AuthorizationCommand
	data object RestoreClicked : AuthorizationCommand
	object SupportClicked : AuthorizationCommand
	object RegistrationClicked : AuthorizationCommand
	object YandexClicked : AuthorizationCommand
}