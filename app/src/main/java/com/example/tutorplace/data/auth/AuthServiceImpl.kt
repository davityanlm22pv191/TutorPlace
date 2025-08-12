package com.example.tutorplace.data.auth

import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthServiceImpl @Inject constructor() : AuthService {

	override suspend fun restorePassword(email: String): Result<Boolean> {
		delay(1500L)
		return Result.success(true)
	}
}