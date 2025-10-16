package com.example.tutorplace.domain.usecases.onboarding.model

import com.example.tutorplace.domain.model.Sex

data class PlatformAccessDataBody(
	val userName: String,
	val password: String,
	val sex: Sex
)
