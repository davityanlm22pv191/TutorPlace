package com.example.tutorplace.helpers

import android.util.Patterns
import java.util.Locale

object FormatHelper {

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

	// region ==================== Password =====================

	// endregion

	// region ==================== Time =====================

	fun formatTime(seconds: Int): String {
		val minutes = seconds / 60
		val remainingSeconds = seconds % 60
		return String.format(Locale.getDefault(), "%02d:%02d", minutes, remainingSeconds)
	}

	// endregion
}