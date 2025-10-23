package com.example.tutorplace.ui.screens.mycourses.presentation

import com.example.tutorplace.ui.base.BaseState

data class MyCoursesState(
	val isLoading: Boolean = false,
	val throwable: Throwable? = null
) : BaseState