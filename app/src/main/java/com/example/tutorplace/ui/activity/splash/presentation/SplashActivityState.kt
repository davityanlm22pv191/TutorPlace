package com.example.tutorplace.ui.activity.splash.presentation

import com.example.tutorplace.ui.base.BaseState

data class SplashActivityState(
	override val isLoading: Boolean = false,
	override val throwable: Throwable? = null
) : BaseState