package com.example.tutorplace.ui.screens.auth.restorepassword.presentation

import com.example.tutorplace.ui.base.BaseEvent

sealed interface RestorePasswordEvent : BaseEvent {
	object OnAuthorization: RestorePasswordEvent
}