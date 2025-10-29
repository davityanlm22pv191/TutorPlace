package com.example.tutorplace.ui.screens.home.presentation

import androidx.lifecycle.viewModelScope
import com.example.tutorplace.data.fortunewheel.FortuneWheelService
import com.example.tutorplace.data.profile.storage.ProfileStorage
import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent.Domain.FortuneWheelFailed
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent.Domain.FortuneWheelLoaded
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent.Domain.FortuneWheelLoading
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent.Domain.SetProfileInfo
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent.UI
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent.UI.FortuneWheelClicked
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent.UI.FortuneWheelInformationClicked
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent.UI.NotificationClicked
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent.UI.ProfileClicked
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent.UI.SearchClicked
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val profileStorage: ProfileStorage,
	private val fortuneWheelService: FortuneWheelService,
) : BaseViewModel<HomeEvent, HomeState, HomeEffect>() {

	init {
		collectProfileShortInfo()
		loadFortuneWheelLastRotation()
	}

	override fun initialState() = HomeState()

	override fun onEvent(event: HomeEvent) = when (event) {
		is UI -> onUiEvent(event)
		is HomeEvent.Domain -> onDomainEvent(event)
	}

	private fun onDomainEvent(event: HomeEvent.Domain) = when (event) {
		is FortuneWheelFailed,
		is FortuneWheelLoaded,
		is FortuneWheelLoading,
		is SetProfileInfo -> setState(HomeReducer.reduce(state.value, event))
	}

	private fun onUiEvent(event: UI) = when (event) {
		is NotificationClicked -> sendEffect(HomeEffect.NavigateToMail)
		is ProfileClicked -> sendEffect(HomeEffect.NavigateToProfile)
		is SearchClicked -> sendEffect(HomeEffect.NavigateToSearchScreen)
		is FortuneWheelClicked -> sendEffect(HomeEffect.NavigateToFortuneWheelScreen)
		is FortuneWheelInformationClicked -> sendEffect(HomeEffect.NavigateToFortuneWheelInformationBottomSheet)
	}

	private fun collectProfileShortInfo() {
		viewModelScope.launch {
			profileStorage.profileShortInfo.collect { value ->
				value?.let { profileShortInfo -> onDomainEvent(SetProfileInfo(profileShortInfo)) }
			}
		}
	}

	private fun loadFortuneWheelLastRotation() {
		viewModelScope.launch {
			onDomainEvent(FortuneWheelLoading)
			val response = fortuneWheelService.getLastRotation()
			if (response.isSuccessful) {
				onDomainEvent(FortuneWheelLoaded(response.body()!!.lastSpinTime))
			} else {
				onDomainEvent(FortuneWheelFailed(Throwable(response.message())))
			}
		}
	}
}