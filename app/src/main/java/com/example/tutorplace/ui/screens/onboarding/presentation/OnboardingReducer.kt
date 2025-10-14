package com.example.tutorplace.ui.screens.onboarding.presentation

import com.example.tutorplace.domain.model.failure
import com.example.tutorplace.domain.model.loaded
import com.example.tutorplace.ui.base.BaseReducer
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.HelpYouStay
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.KnowledgeFromMasters
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.Main
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.MoreOpportunities
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.ProvideDetails
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.Quizzes
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.SpendYourTimeProductively
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.TellUsAboutInterests

object OnboardingReducer : BaseReducer<OnboardingState, OnboardingEvent> {

	override fun reduce(
		oldState: OnboardingState,
		event: OnboardingEvent
	): OnboardingState {
		return when (event) {
			is OnboardingEvent.NextStepClicked -> reduceNextStep(oldState)
			is OnboardingEvent.PreviousStepClicked -> reducePreviousStep(oldState)
			is OnboardingEvent.ProductNameLoadFail -> reduceProductNameLoadFail(
				oldState as Quizzes,
				event.throwable
			)
			is OnboardingEvent.ProductNameLoaded -> reduceProductNameLoaded(
				oldState as Quizzes,
				event.productName
			)
		}
	}

	private fun reduceNextStep(oldState: OnboardingState): OnboardingState {
		return when (oldState) {
			is Quizzes -> Main
			is Main -> ProvideDetails()
			is ProvideDetails -> MoreOpportunities
			is MoreOpportunities -> KnowledgeFromMasters
			is KnowledgeFromMasters -> TellUsAboutInterests()
			is TellUsAboutInterests -> HelpYouStay()
			is HelpYouStay -> SpendYourTimeProductively
			is SpendYourTimeProductively -> Main
		}
	}

	private fun reducePreviousStep(oldState: OnboardingState): OnboardingState {
		return when (oldState) {
			is Main -> Quizzes()
			is ProvideDetails -> Main
			is MoreOpportunities -> ProvideDetails()
			is KnowledgeFromMasters -> MoreOpportunities
			is TellUsAboutInterests -> KnowledgeFromMasters
			is HelpYouStay -> TellUsAboutInterests()
			is SpendYourTimeProductively -> HelpYouStay()
			is Quizzes -> SpendYourTimeProductively
		}
	}

	private fun reduceProductNameLoaded(
		oldState: Quizzes,
		productName: String
	): OnboardingState = oldState.copy(productName = oldState.productName.loaded(productName))

	private fun reduceProductNameLoadFail(
		oldState: Quizzes,
		throwable: Throwable
	): OnboardingState = oldState.copy(productName = oldState.productName.failure(throwable))
}