package com.example.tutorplace.helpers

import android.util.Patterns

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
}