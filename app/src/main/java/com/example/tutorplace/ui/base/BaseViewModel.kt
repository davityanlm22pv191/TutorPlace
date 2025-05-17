package com.example.tutorplace.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event : BaseEvent, Command : BaseCommand> : ViewModel() {

	private companion object {
		const val EVENTS_BUFFER_SIZE = 10
	}

	private val mutableEvent: MutableSharedFlow<Event> = MutableSharedFlow(
		replay = EVENTS_BUFFER_SIZE
	)

	val event: SharedFlow<Event> = mutableEvent

	protected fun setEvent(event: Event) = viewModelScope.launch { mutableEvent.emit(event) }

	abstract fun handleCommand(command: Command)
}