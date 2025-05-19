package com.example.tutorplace.ui.base

interface BaseReducer<State: BaseState, Command: BaseCommand> {
	fun reduce(oldState: State, command: Command): State
}