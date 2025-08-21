package com.example.tutorplace.data.credentials

import kotlinx.coroutines.flow.Flow

interface CredentialsStorage {

	suspend fun saveToken(token: String)

	suspend fun clearToken()

	suspend fun isAuthorized(): Flow<Boolean>
}