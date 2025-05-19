package com.example.tutorplace.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<Command : BaseCommand, Event : BaseEvent, State : BaseState> :
	ViewModel() {

	private val _event = Channel<Event>(Channel.BUFFERED)
	val event = _event.receiveAsFlow()

	private val _state = MutableStateFlow(initialState())
	val state: StateFlow<State> = _state

	abstract fun initialState(): State

	protected fun setState(newState: State) {
		_state.update { newState }
	}

	protected fun sendEvent(event: Event) {
		viewModelScope.launch { _event.send(event) }
	}

	abstract fun handleCommand(command: Command)
}