package com.example.tutorplace.ui.screens.auth.authorization.presentation

import com.example.tutorplace.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor() :
	BaseViewModel<AuthorizationEvent, AuthorizationCommand>() {

	override fun handleCommand(command: AuthorizationCommand) = Unit
}