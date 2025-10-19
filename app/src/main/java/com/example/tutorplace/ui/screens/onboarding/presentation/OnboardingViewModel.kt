package com.example.tutorplace.ui.screens.onboarding.presentation

import androidx.lifecycle.viewModelScope
import com.example.tutorplace.data.onboarding.model.PlatformAccessDataBody
import com.example.tutorplace.domain.usecases.onboarding.GetOnboardingInfoUseCase
import com.example.tutorplace.domain.usecases.onboarding.PostOnboardingInfoUseCase
import com.example.tutorplace.helpers.FormatHelper
import com.example.tutorplace.ui.base.BaseViewModel
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.NameValidError
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.NextStepClicked
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.OnboardingInfoLoadFail
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.OnboardingInfoLoaded
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.OnboardingInfoLoading
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.PasswordValidError
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.RepeatPasswordValidError
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.SexError
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.Step
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
	private val getOnboardingInfoUseCase: GetOnboardingInfoUseCase,
	private val postOnboardingInfoUseCase: PostOnboardingInfoUseCase,
) : BaseViewModel<OnboardingEvent, OnboardingState, OnboardingEffect>() {

	// THIS IS MOCK LINK FOR IMAGES
	// https://iili.io/Kk4qtK7.png
	// https://iili.io/Kkri8V2.png
	// https://iili.io/KkriSPS.png

	// TODO
	//  1) Сделать один when для When'a на проверку шага, создать UiState,
	//  где будет mainButtonTitle, title, description, separator и тд,
	//  2) далее реализовать mock API для HelpToStay экрана
	//  3) Пофиксить карусель с картинками на MoreOpportunities и на KnowledgeFromMasters
	//  4) На реальном телефоне проверить, чтобы цвет текста был везде черным
	//  5) Подумать как распределить Event'ы так, чтобы можно было удобно их использовать UI/Domain
	//  6) Радоваться

	init {
		loadGiftProductName()
	}

	override fun initialState() = OnboardingState(step = Step.CONGRATULATIONS)

	override fun onEvent(event: OnboardingEvent) = when (event) {
		is NextStepClicked -> checkCurrentStateAndNavigateToNextStep()
		else -> setState(OnboardingReducer.reduce(state.value, event))
	}

	private fun checkCurrentStateAndNavigateToNextStep() {
		when (state.value.step) {
			Step.PROVIDE_DETAILS -> setState(
				OnboardingReducer.reduce(
					state.value,
					NextStepClicked
				)
			) // TODO processProvideDetailsStep()
			Step.TELL_US_ABOUT_INTERESTS -> setState(
				OnboardingReducer.reduce(
					state.value,
					NextStepClicked
				)
			)// TODO processTellUsAboutInterestsStep()
			Step.SPEND_YOUR_TIME_PRODUCTIVELY -> sendEffect(OnboardingEffect.Hide)
			Step.HELP_YOU_STAY -> processHelpYouStayStep()
			Step.WELCOME,
			Step.MORE_OPPORTUNITIES,
			Step.KNOWLEDGE_FROM_MASTERS,
			Step.CONGRATULATIONS -> {
				if (!state.value.onboardingInfo.isLoading) {
					setState(OnboardingReducer.reduce(state.value, NextStepClicked))
				}
			}
		}
	}

	private fun processHelpYouStayStep() {

	}

	private fun processTellUsAboutInterestsStep() {
		viewModelScope.launch {
			val isAllDataValid = state.value.selectedInterestsIds.isNotEmpty()
			if (isAllDataValid) {
				setState(OnboardingReducer.reduce(state.value, OnboardingInfoLoading))
				postOnboardingInfoUseCase
					.postInterests(state.value.selectedInterestsIds)
					.onSuccess {
						setState(
							OnboardingReducer.reduce(
								state.value,
								OnboardingInfoLoaded(state.value.onboardingInfo.data)
							)
						)
						setState(OnboardingReducer.reduce(state.value, NextStepClicked))
					}
					.onFailure { throwable ->
						setState(
							OnboardingReducer.reduce(state.value, OnboardingInfoLoadFail(throwable))
						)
					}
			}
		}
	}

	private fun processProvideDetailsStep() {
		viewModelScope.launch {
			val isAllDataValid = checkProvideDetailsStep()
			if (isAllDataValid) {
				setState(OnboardingReducer.reduce(state.value, OnboardingInfoLoading))
				postOnboardingInfoUseCase
					.postPlatformAccessData(
						PlatformAccessDataBody(
							userName = state.value.userName.value,
							password = state.value.password.value,
							sex = state.value.sex ?: return@launch
						)
					)
					.onSuccess {
						setState(
							OnboardingReducer.reduce(
								state.value,
								OnboardingInfoLoaded(state.value.onboardingInfo.data)
							)
						)
						setState(OnboardingReducer.reduce(state.value, NextStepClicked))
					}
					.onFailure { throwable ->
						setState(
							OnboardingReducer.reduce(state.value, OnboardingInfoLoadFail(throwable))
						)
					}
			}
		}
	}

	private fun checkProvideDetailsStep(): Boolean = with(state.value) {
		val errorsEvents = buildList {
			if (!FormatHelper.isValidName(userName.value)) add(NameValidError)
			if (!FormatHelper.isValidPassword(password.value)) add(PasswordValidError)
			if (password.value != repeatedPassword.value ||
				!FormatHelper.isValidPassword(repeatedPassword.value)
			) add(RepeatPasswordValidError)
			if (sex == null) add(SexError)
		}
		errorsEvents.forEach { error -> setState(OnboardingReducer.reduce(state.value, error)) }
		return errorsEvents.isEmpty()
	}

	private fun loadGiftProductName() {
		viewModelScope.launch {
			getOnboardingInfoUseCase
				.execute()
				.onSuccess { onboardingInfo ->
					setState(
						OnboardingReducer.reduce(state.value, OnboardingInfoLoaded(onboardingInfo))
					)
				}
				.onFailure { throwable ->
					setState(
						OnboardingReducer.reduce(state.value, OnboardingInfoLoadFail(throwable))
					)
				}
		}
	}
}