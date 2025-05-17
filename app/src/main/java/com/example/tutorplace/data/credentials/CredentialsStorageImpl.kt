package com.example.tutorplace.data.credentials

import javax.inject.Inject

class CredentialsStorageImpl @Inject constructor() : CredentialsStorage {

	override fun isAuthorized(): Boolean = false
}