package com.example.tutorplace.ui.screens.home.presentation

import com.example.tutorplace.data.profile.model.ProfileShortInfo
import com.example.tutorplace.ui.base.BaseEvent

sealed interface HomeEvent : BaseEvent {
	sealed interface Domain : HomeEvent {
		data class SetProfileInfo(val profileShortInfo: ProfileShortInfo) : Domain
	}

	sealed interface UI : HomeEvent {
		data object NotificationClicked : UI
		data object SearchClicked : UI
		data object ProfileClicked : UI
	}
}