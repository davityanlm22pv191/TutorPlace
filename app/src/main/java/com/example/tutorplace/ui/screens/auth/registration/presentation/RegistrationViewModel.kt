package com.example.tutorplace.ui.screens.auth.registration.presentation

import androidx.lifecycle.viewModelScope
import com.example.tutorplace.data.credentials.CredentialsStorage
import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationEvent.Domain
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationEvent.UI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
	private val credentialsStorage: CredentialsStorage
) : BaseViewModel<RegistrationEvent, RegistrationState, RegistrationEffect>() {

	override fun initialState() = RegistrationState()

	override fun onEvent(event: RegistrationEvent) = when (event) {
		is UI -> setState(RegistrationReducer.reduce(state.value, event))
		is Domain -> Unit
	}

	fun onFirstStepClicked() {
		setState(RegistrationReducer.reduce(state.value, Domain.SwitchToFirstStep))
	}

	fun onSecondStepClicked() {
		setState(RegistrationReducer.reduce(state.value, Domain.SwitchToSecondStep))
	}

	fun onRegisterClicked() {
		setState(RegistrationReducer.reduce(state.value, Domain.Register))
		if (state.value.firstStep.isNameError) return
		if (state.value.firstStep.isPhoneNumberError) return
		if (state.value.firstStep.isTelegramError) return
		if (state.value.secondStep.isEmailError) return
		if (state.value.secondStep.isPasswordError) return
		if (state.value.secondStep.isConfirmPasswordError) return
		viewModelScope.launch {
			setState(RegistrationReducer.reduce(state.value, UI.RegisterRequested))
			credentialsStorage.saveToken("123123")
			sendEffect(RegistrationEffect.NavigateToHome)
		}
	}

	fun onOfferClicked() = Unit
	fun onTermsClicked() = Unit
	fun onYandexClicked() = Unit
}