package com.example.tutorplace.ui.base

interface BaseReducer<State: BaseState, Intent: BaseIntent> {
	fun reduce(oldState: State, intent: Intent): State
}