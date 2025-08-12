package com.example.tutorplace.ui.screens.auth.registration.presentation

import com.example.tutorplace.ui.base.BaseState

data class RegistrationState(
	override val isLoading: Boolean = false,
	override val throwable: Throwable? = null,
): BaseState