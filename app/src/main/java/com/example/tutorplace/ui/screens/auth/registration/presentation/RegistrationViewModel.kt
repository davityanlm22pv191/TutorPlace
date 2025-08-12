package com.example.tutorplace.ui.screens.auth.registration.presentation

import com.example.tutorplace.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor() :
	BaseViewModel<RegistrationCommand, RegistrationEvent, RegistrationState>() {

	override fun initialState() = RegistrationState()

	override fun handleCommand(command: RegistrationCommand) = Unit
}