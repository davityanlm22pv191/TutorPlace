package com.example.tutorplace.ui.screens.auth.authorization.presentation

import com.example.tutorplace.ui.base.BaseEvent

sealed interface AuthorizationEvent : BaseEvent {
	object OnHome : AuthorizationEvent
}