package com.example.tutorplace.data.onboarding.model

import com.example.tutorplace.domain.model.Sex

data class PlatformAccessDataBody(
	val userName: String,
	val password: String,
	val sex: Sex
)