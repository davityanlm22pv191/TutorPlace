package com.example.tutorplace.ui.activity.splash.presentation

import com.example.tutorplace.ui.base.BaseEvent

sealed interface SplashActivityEvent: BaseEvent {
	data object NavigateToAuth: SplashActivityEvent
	data object NavigateToHome: SplashActivityEvent
}