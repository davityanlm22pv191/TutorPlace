package com.example.tutorplace.ui.screens.onboarding.presentation

import com.example.tutorplace.ui.base.BaseState

data class OnboardingState(
	override val isLoading: Boolean = false,
	override val throwable: Throwable? = null
) : BaseState