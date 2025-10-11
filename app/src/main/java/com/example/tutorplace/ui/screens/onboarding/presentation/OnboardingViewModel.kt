package com.example.tutorplace.ui.screens.onboarding.presentation

import androidx.lifecycle.viewModelScope
import com.example.tutorplace.data.onboarding.OnboardingService
import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.NextStepClicked
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.PreviousStepClicked
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.ProductNameLoadFail
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.ProductNameLoaded
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.HelpYouStay
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.KnowledgeFromMasters
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.Main
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.MoreOpportunities
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.ProvideDetails
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.Quizzes
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.SpendYourTimeProductively
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.TellUsAboutInterests
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

	override fun initialState() = Quizzes()

	override fun onEvent(event: OnboardingEvent) = when (event) {
		is NextStepClicked -> checkCurrentStateAndNavigateToNextStep()
		is PreviousStepClicked -> Unit
		is ProductNameLoaded,
		is ProductNameLoadFail -> Unit
	}

	private fun checkCurrentStateAndNavigateToNextStep() {
		val isAllDataValid = when (state.value) {
			is ProvideDetails -> false
			is TellUsAboutInterests -> false
			is HelpYouStay -> false
			is Main,
			is MoreOpportunities,
			is KnowledgeFromMasters,
			is SpendYourTimeProductively,
			is Quizzes -> true
		}
		if (isAllDataValid) {
			setState(OnboardingReducer.reduce(state.value, NextStepClicked))
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