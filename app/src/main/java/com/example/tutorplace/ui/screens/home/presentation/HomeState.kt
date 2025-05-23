package com.example.tutorplace.ui.screens.home.presentation

import com.example.tutorplace.ui.base.BaseState

data class HomeState(
	override val isLoading: Boolean = false,
	override val throwable: Throwable? = null
) : BaseState