package com.example.tutorplace.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<Event : BaseEvent, State : BaseState, Effect : BaseEffect> :
	ViewModel() {

	private val _event = MutableSharedFlow<Event>()
	val event: SharedFlow<Event>
		get() = _event.asSharedFlow()

	private val _effects = Channel<Effect>(capacity = Channel.CONFLATED)
	val effect = _effects.receiveAsFlow()

	private val _state = MutableStateFlow(initialState())
	val state: StateFlow<State> = _state.asStateFlow()

	abstract fun initialState(): State

	protected fun sendEffect(effect: Effect) {
		_effects.trySend(effect)
	}

	abstract fun onEvent(event: Event)

	protected fun setState(newState: State) {
		_state.tryEmit(newState)
	}
}