package com.example.tutorplace.helpers

import android.util.Patterns
import java.util.Locale

object FormatHelper {

	private const val REQUIRED_NAME_LENGTH = 6
	private const val REQUIRED_PASSWORD_LENGTH = 6

	// region ==================== E-mail =====================

	fun isValidEmail(text: String): Boolean {
		return Patterns.EMAIL_ADDRESS.matcher(text).matches()
	}

	// endregion

	// region ==================== Password =====================

	fun isValidPassword(text: String): Boolean {
		return text.length > REQUIRED_PASSWORD_LENGTH
	}

	// endregion

	// region ==================== Name =====================

	fun isValidName(text: String): Boolean {
		return text.isNotEmpty() && text.all { it.isLetter() } && text.length > REQUIRED_NAME_LENGTH
	}

	// endregion

	// region ==================== Phone =====================

	fun isValidPhone(phone: String): Boolean {
		return true
	}

	// endregion

	// region ==================== Phone =====================

	fun isValidTelegram(telegram: String): Boolean {
		val regex = Regex("^[a-zA-Z0-9][a-zA-Z0-9_]{3,30}[a-zA-Z0-9]$")
		return regex.matches(telegram)
	}

	// endregion

	// region ==================== Time =====================

	fun formatTime(seconds: Int): String {
		val minutes = seconds / 60
		val remainingSeconds = seconds % 60
		return String.format(Locale.getDefault(), "%02d:%02d", minutes, remainingSeconds)
	}

	// endregion
}