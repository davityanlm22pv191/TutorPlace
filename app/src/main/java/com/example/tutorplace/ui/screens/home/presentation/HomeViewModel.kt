package com.example.tutorplace.ui.screens.home.presentation

import com.example.tutorplace.ui.base.BaseViewModel

class HomeViewModel : BaseViewModel<HomeEvent, HomeState, HomeEffect>() {

	override fun initialState() = HomeState()

	override fun onEvent(event: HomeEvent) = Unit
}