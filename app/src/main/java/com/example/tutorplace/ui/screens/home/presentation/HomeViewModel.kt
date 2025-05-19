package com.example.tutorplace.ui.screens.home.presentation

import com.example.tutorplace.ui.base.BaseViewModel

class HomeViewModel : BaseViewModel<HomeCommand, HomeEvent, HomeState>() {

	override fun initialState() = HomeState()

	override fun handleCommand(command: HomeCommand) = Unit
}