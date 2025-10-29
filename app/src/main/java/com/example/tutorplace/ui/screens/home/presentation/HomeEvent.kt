package com.example.tutorplace.ui.screens.home.presentation

import com.example.tutorplace.data.profile.model.ProfileShortInfo
import com.example.tutorplace.ui.base.BaseEvent
import java.time.LocalDateTime

sealed interface HomeEvent : BaseEvent {
	sealed interface Domain : HomeEvent {
		data class SetProfileInfo(val profileShortInfo: ProfileShortInfo) : Domain
		data object FortuneWheelLoading : Domain
		data class FortuneWheelLoaded(val lastRotation: LocalDateTime) : Domain
		data class FortuneWheelFailed(val throwable: Throwable) : Domain
	}

	sealed interface UI : HomeEvent {
		data object NotificationClicked : UI
		data object SearchClicked : UI
		data object ProfileClicked : UI
		data object FortuneWheelClicked: UI
		data object FortuneWheelInformationClicked: UI
	}
}