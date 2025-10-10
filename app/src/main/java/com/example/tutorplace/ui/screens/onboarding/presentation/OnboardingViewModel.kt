package com.example.tutorplace.ui.screens.onboarding.presentation

import androidx.lifecycle.viewModelScope
import com.example.tutorplace.data.onboarding.OnboardingService
import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.NextStepClicked
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.PreviousStepClicked
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.ProductNameLoadFail
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.ProductNameLoaded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
	private val onboardingService: OnboardingService,
) :
	BaseViewModel<OnboardingEvent, OnboardingState, OnboardingEffect>() {

	init {
		loadGiftProductName()
	}

	override fun initialState() = OnboardingState.Quizzes()

	override fun onEvent(event: OnboardingEvent) = when (event) {
		is NextStepClicked,
		is PreviousStepClicked -> setState(OnboardingReducer.reduce(state.value, event))
		is ProductNameLoaded,
		is ProductNameLoadFail -> {
		}
	}

	private fun loadGiftProductName() {
		viewModelScope.launch {
			onboardingService
				.getGiftProduct()
				.onSuccess { response ->
					setState(
						OnboardingReducer.reduce(
							state.value,
							ProductNameLoaded(response.productName)
						)
					)
				}
				.onFailure { throwable ->
					setState(
						OnboardingReducer.reduce(
							state.value,
							ProductNameLoadFail(throwable)
						)
					)
				}
		}
	}
}