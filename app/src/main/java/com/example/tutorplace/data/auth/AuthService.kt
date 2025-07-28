package com.example.tutorplace.data.auth

interface AuthService {
	suspend fun restorePassword(email: String): Result<Boolean>
}