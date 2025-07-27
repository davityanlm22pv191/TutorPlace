package com.example.tutorplace.ui.screens.auth.restorepassword.presentation

import com.example.tutorplace.ui.base.BaseState

data class RestorePasswordState(
	override val isLoading: Boolean = false,
	override val throwable: Throwable? = null,
	val isRestoreButtonEnabled: Boolean = false,
	val email: String = "",
	val isEmailError: Boolean = false
) : BaseState