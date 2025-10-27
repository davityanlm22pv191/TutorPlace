package com.example.tutorplace.ui.screens.home.presentation

import androidx.lifecycle.viewModelScope
import com.example.tutorplace.data.profile.storage.ProfileStorage
import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent.Domain.SetProfileInfo
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent.UI
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent.UI.NotificationClicked
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent.UI.ProfileClicked
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent.UI.SearchClicked
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val profileStorage: ProfileStorage,
) : BaseViewModel<HomeEvent, HomeState, HomeEffect>() {

	init {
		collectProfileShortInfo()
	}

	override fun initialState() = HomeState()

	override fun onEvent(event: HomeEvent) = when (event) {
		is UI -> onUiEvent(event)
		is HomeEvent.Domain -> onDomainEvent(event)
	}

	private fun onDomainEvent(event: HomeEvent.Domain) = when (event) {
		is SetProfileInfo -> setState(HomeReducer.reduce(state.value, event))
	}

	private fun onUiEvent(event: UI) = when (event) {
		is NotificationClicked -> sendEffect(HomeEffect.NavigateToMail)
		is ProfileClicked -> sendEffect(HomeEffect.NavigateToProfile)
		is SearchClicked -> sendEffect(HomeEffect.NavigateToSearchScreen)
	}

	private fun collectProfileShortInfo() {
		viewModelScope.launch {
			profileStorage.profileShortInfo.collect { value ->
				value?.let { profileShortInfo -> onDomainEvent(SetProfileInfo(profileShortInfo)) }
			}
		}
	}
}