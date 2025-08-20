package com.example.tutorplace.ui.screens.auth.authorization.presentation

import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.EmailChanged
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.PasswordChanged
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor() :
	BaseViewModel<AuthorizationEvent, AuthorizationState, AuthorizationEffect>() {

	override fun initialState() = AuthorizationState()

	override fun onEvent(event: AuthorizationEvent) = when (event) {
		is EmailChanged -> setState(AuthorizationReducer.reduce(state.value, event))
		is PasswordChanged -> setState(AuthorizationReducer.reduce(state.value, event))
	}

	fun onRestoreClicked() {
		sendEffect(AuthorizationEffect.NavigateToRestorePassword)
	}

	fun onRegistrationClicked() {
		sendEffect(AuthorizationEffect.NavigateToRegistration)
	}

	fun onSupportClicked() = Unit
	fun onAuthorizeClicked() = Unit
	fun onYandexClicked() = Unit
}