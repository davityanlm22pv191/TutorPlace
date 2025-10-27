package com.example.tutorplace.ui.base.main.presentation

import com.example.tutorplace.ui.base.BaseReducer

object MainScreenReducer : BaseReducer<MainScreenState, MainScreenEvent> {
	override fun reduce(
		oldState: MainScreenState,
		event: MainScreenEvent
	): MainScreenState = oldState
}