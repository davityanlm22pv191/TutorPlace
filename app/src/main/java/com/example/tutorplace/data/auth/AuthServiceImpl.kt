package com.example.tutorplace.data.auth

import com.example.tutorplace.helpers.DateHelper.toDate
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys
import kotlinx.coroutines.delay
import java.time.ZonedDateTime
import javax.inject.Inject

class AuthServiceImpl @Inject constructor() : AuthService {

	override suspend fun authorizeWithEmail(email: String, password: String): Result<String?> {
		delay(1500L)
		val token = (0..1000)
			.random()
			.takeIf { randomInt -> randomInt >= 400 }
			?.let { randomInt ->
				val userId = randomInt.toString()
				val issuedAt = ZonedDateTime.now().toDate()
				Jwts
					.builder()
					.subject(userId)
					.subject(email)
					.subject(password)
					.issuedAt(issuedAt)
					.signWith(
						Keys.hmacShaKeyFor(
							Encoders.BASE64.encode(email.toByteArray()).toByteArray()
						)
					)
					.compact()
			}
		return Result.success(token)
	}

	override suspend fun register(
		name: String,
		phoneNumber: String,
		telegram: String,
		email: String,
		password: String,
	): Result<String?> {
		delay(1500L)
		val token = (0..1000)
			.random()
			.takeIf { randomInt -> randomInt >= 400 }
			?.let { randomInt ->
				val userId = randomInt.toString()
				val issuedAt = ZonedDateTime.now().toDate()
				Jwts
					.builder()
					.subject(userId)
					.subject(name)
					.subject(phoneNumber)
					.subject(email)
					.subject(password)
					.issuedAt(issuedAt)
					.signWith(
						Keys.hmacShaKeyFor(
							Encoders.BASE64.encode(email.toByteArray()).toByteArray()
						)
					)
					.compact()
			}
		return Result.success(token)
	}

	override suspend fun restorePassword(email: String): Result<Boolean> {
		delay(1500L)
		return Result.success(true)
	}
}