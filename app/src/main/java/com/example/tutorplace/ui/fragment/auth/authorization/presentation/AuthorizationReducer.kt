package com.example.tutorplace.ui.fragment.auth.authorization.presentation

import com.example.tutorplace.ui.base.BaseReducer

class AuthorizationReducer : BaseReducer<AuthorizationState, AuthorizationEvent>{
	override fun reduce(
		oldState: AuthorizationState,
		intent: AuthorizationEvent
	): AuthorizationState {
		return oldState
	}
}