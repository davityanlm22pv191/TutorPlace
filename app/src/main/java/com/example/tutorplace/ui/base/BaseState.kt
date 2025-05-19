package com.example.tutorplace.ui.base

interface BaseState {
	val isLoading: Boolean
	val throwable: Throwable?
}