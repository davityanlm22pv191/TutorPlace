package com.example.tutorplace.ui.screens.auth.authorization.presentation

import com.example.tutorplace.ui.base.BaseState

data class AuthorizationState(
	override val isLoading: Boolean = false,
	override val throwable: Throwable? = null,
	val isLoginButtonEnabled: Boolean = false,
	val email: String = "",
	val isEmailError: Boolean = false,
	val password: String = "",
	val isPasswordError: Boolean = false,
): BaseState