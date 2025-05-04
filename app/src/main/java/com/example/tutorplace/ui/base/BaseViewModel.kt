package com.example.tutorplace.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<Intent: BaseIntent, Command: BaseCommand>: ViewModel() {

	abstract fun handleCommand(command: Command)
}