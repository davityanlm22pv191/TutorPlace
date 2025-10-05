package com.example.tutorplace.domain.usecases

import com.example.tutorplace.data.auth.AuthService
import com.example.tutorplace.data.credentials.CredentialsStorage
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
	private val authService: AuthService,
	private val credentialsStorage: CredentialsStorage,
) {

	suspend fun execute(
		name: String,
		phoneNumber: String,
		telegram: String,
		email: String,
		password: String,
	): Boolean {
		return authService
			.register(name, phoneNumber, telegram, email, password)
			.onSuccess { token ->
				if (token != null) {
					credentialsStorage.saveToken(token)
				}
			}
			.isSuccess
	}
}