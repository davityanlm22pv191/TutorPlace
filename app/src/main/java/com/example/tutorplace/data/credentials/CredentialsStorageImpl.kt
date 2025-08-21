package com.example.tutorplace.data.credentials

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CredentialsStorageImpl @Inject constructor(
	@param:ApplicationContext private val context: Context
) : CredentialsStorage {

	private val Context.dataStore by preferencesDataStore(name = "credentials")
	private val tokenPreferenceKey = stringPreferencesKey("TOKEN_PREFERENCE_KEY")

	override suspend fun saveToken(token: String) {
		context.dataStore.edit { prefs -> prefs[tokenPreferenceKey] = token; delay(2000L) }
	}

	override suspend fun isAuthorized(): Flow<Boolean> {
		return getToken().map { token -> !token.isNullOrEmpty() }
	}

	override suspend fun clearToken() {
		context.dataStore.edit { prefs -> prefs.remove(tokenPreferenceKey) }
	}

	private fun getToken(): Flow<String?> {
		return context.dataStore.data.map { prefs -> prefs[tokenPreferenceKey] }
	}
}