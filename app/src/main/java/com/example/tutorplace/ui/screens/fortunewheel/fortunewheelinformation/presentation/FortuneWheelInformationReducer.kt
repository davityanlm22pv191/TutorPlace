package com.example.tutorplace.ui.screens.fortunewheel.fortunewheelinformation.presentation

import com.example.tutorplace.ui.base.BaseReducer

object FortuneWheelInformationReducer :
	BaseReducer<FortuneWheelInformationState, FortuneWheelInformationEvent> {

	override fun reduce(
		oldState: FortuneWheelInformationState,
		event: FortuneWheelInformationEvent
	): FortuneWheelInformationState {
		return when (event) {
			else -> oldState
		}
	}
}
