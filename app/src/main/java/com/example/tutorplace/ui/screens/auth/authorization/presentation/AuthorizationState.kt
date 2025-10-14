package com.example.tutorplace.ui.screens.auth.authorization.presentation

import com.example.tutorplace.ui.base.BaseState

data class AuthorizationState(
	val isLoading: Boolean = false,
	val throwable: Throwable? = null,
	val email: String = "",
	val isEmailError: Boolean = false,
	val password: String = "",
	val isPasswordError: Boolean = false,
) : BaseState