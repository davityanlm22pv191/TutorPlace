package com.example.tutorplace.ui.screens.auth.authorization.presentation

import com.example.tutorplace.ui.base.BaseReducer

object AuthorizationReducer : BaseReducer<AuthorizationState, AuthorizationEvent>{
	override fun reduce(
		oldState: AuthorizationState,
		event: AuthorizationEvent
	): AuthorizationState {
		return oldState
	}
}