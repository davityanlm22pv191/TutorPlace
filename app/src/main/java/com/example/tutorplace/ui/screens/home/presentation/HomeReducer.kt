package com.example.tutorplace.ui.screens.home.presentation

import com.example.tutorplace.ui.base.BaseReducer

object HomeReducer : BaseReducer<HomeState, HomeEvent> {

	override fun reduce(oldState: HomeState, event: HomeEvent): HomeState = when (event) {
		is HomeEvent.Domain -> when (event) {
			is HomeEvent.Domain.SetProfileInfo -> oldState.copy(profileShortInfo = event.profileShortInfo)
		}
		is HomeEvent.UI -> oldState
	}
}