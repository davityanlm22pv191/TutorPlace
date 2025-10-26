package com.example.tutorplace.ui.base.main.presentation

import com.example.tutorplace.ui.base.BaseState

data class MainScreenState(
	val isLoading: Boolean = false,
	val errorMessage: String? = null,
): BaseState
