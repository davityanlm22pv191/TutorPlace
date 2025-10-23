package com.example.tutorplace.ui.screens.tasks.presentation

import com.example.tutorplace.ui.base.BaseState

data class TasksState(
	val isLoading: Boolean = false,
	val throwable: Throwable? = null
) : BaseState