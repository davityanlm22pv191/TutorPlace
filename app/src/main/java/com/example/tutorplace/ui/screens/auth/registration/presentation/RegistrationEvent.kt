package com.example.tutorplace.ui.screens.auth.registration.presentation

import com.example.tutorplace.ui.base.BaseEvent

sealed interface RegistrationEvent : BaseEvent {
	object OnHome: RegistrationEvent
}