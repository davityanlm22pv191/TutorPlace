package com.example.tutorplace.ui.screens.auth.registration.presentation

import com.example.tutorplace.ui.base.BaseState

data class RegistrationState(
	override val isLoading: Boolean = false,
	override val throwable: Throwable? = null,
	val registrationStep: RegistrationStep = RegistrationStep.FirstStep()
) : BaseState {

	sealed interface RegistrationStep {
		data class FirstStep(
			val name: String = "",
			val isNameError: Boolean = false,
			val phoneNumber: String = "",
			val isPhoneNumberError: Boolean = false,
			val telegram: String = "",
			val isTelegramError: Boolean = false,
		) : RegistrationStep

		data class SecondStep(
			val email: String = "",
			val isEmailError: Boolean = false,
			val password: String = "",
			val isPasswordError: Boolean = false,
			val confirmPassword: String = "",
			val isConfirmPasswordError: Boolean = false,
		) : RegistrationStep
	}
}