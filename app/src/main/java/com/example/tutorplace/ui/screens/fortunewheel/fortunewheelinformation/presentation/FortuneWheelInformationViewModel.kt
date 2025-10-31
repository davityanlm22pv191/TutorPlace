package com.example.tutorplace.ui.screens.fortunewheel.fortunewheelinformation.presentation

import com.example.tutorplace.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FortuneWheelInformationViewModel @Inject constructor() :
	BaseViewModel<FortuneWheelInformationEvent, FortuneWheelInformationState, FortuneWheelInformationEffect>() {

	override fun initialState(): FortuneWheelInformationState = FortuneWheelInformationState

	override fun onEvent(event: FortuneWheelInformationEvent) {}
}
