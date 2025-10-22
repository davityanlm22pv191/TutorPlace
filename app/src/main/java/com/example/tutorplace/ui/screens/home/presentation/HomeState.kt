package com.example.tutorplace.ui.screens.home.presentation

import com.example.tutorplace.ui.base.BaseState

data class HomeState(
	val isLoading: Boolean = false,
	val throwable: Throwable? = null
) : BaseState