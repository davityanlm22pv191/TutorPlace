package com.example.tutorplace.ui.base

interface BaseReducer<State: BaseState, Event: BaseEvent> {
	fun reduce(oldState: State, intent: Event): State
}