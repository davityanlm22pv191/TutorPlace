package com.example.tutorplace.ui.screens.auth.registration.presentation

import com.example.tutorplace.helpers.FormatHelper
import com.example.tutorplace.ui.base.BaseReducer
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.ConfirmPasswordChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.EmailChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.NameChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.OnFirstStep
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.OnNextClicked
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.OnRegistrationClicked
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.PasswordChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.PhoneChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationCommand.TelegramChanged

object RegistrationReducer : BaseReducer<RegistrationState, RegistrationCommand> {
	override fun reduce(
		oldState: RegistrationState,
		command: RegistrationCommand
	): RegistrationState = when (command) {
		is NameChanged -> reduceNameChanged(oldState, command)
		is PhoneChanged -> reducePhoneChanged(oldState, command)
		is TelegramChanged -> reduceTelegramChanged(oldState, command)
		is EmailChanged -> reduceEmailChanged(oldState, command)
		is PasswordChanged -> reducePasswordChanged(oldState, command)
		is ConfirmPasswordChanged -> reduceConfirmPasswordChanged(oldState, command)
		is OnNextClicked -> reduceOnNext(oldState)
		is OnRegistrationClicked -> reduceOnRegistrationClicked(oldState)
		is OnFirstStep -> oldState.copy(currentStep = RegistrationState.RegistrationStep.FirstStep::class)
		else -> oldState
	}

	private fun reduceNameChanged(
		oldState: RegistrationState,
		command: NameChanged
	): RegistrationState {
		return oldState.copy(
			firstStep = oldState.firstStep.copy(
				name = command.enteredName,
				isNameError = false
			)
		)
	}

	private fun reducePhoneChanged(
		oldState: RegistrationState,
		command: PhoneChanged
	): RegistrationState {
		return oldState.copy(
			firstStep = oldState.firstStep.copy(
				phoneNumber = command.enteredPhone,
				isPhoneNumberError = false
			)
		)
	}

	private fun reduceTelegramChanged(
		oldState: RegistrationState,
		command: TelegramChanged
	): RegistrationState {
		return oldState.copy(
			firstStep = oldState.firstStep.copy(
				telegram = command.enteredTelegram,
				isTelegramError = false
			)
		)
	}

	private fun reduceEmailChanged(
		oldState: RegistrationState,
		command: EmailChanged
	): RegistrationState {
		return oldState.copy(
			secondStep = oldState.secondStep.copy(
				email = command.enteredEmail,
				isEmailError = false
			)
		)
	}

	private fun reducePasswordChanged(
		oldState: RegistrationState,
		command: PasswordChanged
	): RegistrationState {
		return oldState.copy(
			secondStep = oldState.secondStep.copy(
				password = command.enteredPassword,
				isPasswordError = false
			)
		)
	}

	private fun reduceConfirmPasswordChanged(
		oldState: RegistrationState,
		command: ConfirmPasswordChanged
	): RegistrationState {
		return oldState.copy(
			secondStep = oldState.secondStep.copy(
				confirmPassword = command.enteredConfirmPassword,
				isConfirmPasswordError = false
			)
		)
	}

	private fun reduceOnNext(oldState: RegistrationState): RegistrationState {
		val isNameError = FormatHelper.isValidName(oldState.firstStep.name).not()
		val isPhoneNumberError = FormatHelper.isValidPhone(oldState.firstStep.phoneNumber).not()
		val isTelegramError = FormatHelper.isValidTelegram(oldState.firstStep.telegram).not()
		return if (isNameError || isPhoneNumberError || isTelegramError) {
			oldState.copy(
				firstStep = oldState.firstStep.copy(
					isNameError = isNameError,
					isPhoneNumberError = isPhoneNumberError,
					isTelegramError = isTelegramError
				)
			)
		} else {
			oldState.copy(currentStep = RegistrationState.RegistrationStep.SecondStep::class)
		}
	}

	private fun reduceOnRegistrationClicked(oldState: RegistrationState): RegistrationState {
		val isEmailError = FormatHelper.isValidEmail(oldState.secondStep.email).not()
		val isPasswordError = FormatHelper.isValidPassword(oldState.secondStep.password).not()
		val isConfirmPasswordError = FormatHelper.isValidPassword(oldState.secondStep.password)
			.not() || oldState.secondStep.password != oldState.secondStep.confirmPassword
		return oldState.copy(
			secondStep = oldState.secondStep.copy(
				isEmailError = isEmailError,
				isPasswordError = isPasswordError,
				isConfirmPasswordError = isConfirmPasswordError
			)
		)
	}
}