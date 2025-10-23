package com.example.tutorplace.ui.screens.catalog.presentation

import com.example.tutorplace.ui.base.BaseState

data class CatalogState(
	val isLoading: Boolean = false,
	val throwable: Throwable? = null
) : BaseState