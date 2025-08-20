package com.example.tutorplace.ui.screens.auth.authorization.presentation

import com.example.tutorplace.ui.base.BaseEvent

sealed interface AuthorizationEvent : BaseEvent {
	data class EmailChanged(val enteredEmail: String) : AuthorizationEvent
	data class PasswordChanged(val enteredPassword: String) : AuthorizationEvent
}