package com.example.tutorplace.ui.screens.onboarding.presentation

import com.example.tutorplace.domain.model.Sex
import com.example.tutorplace.ui.base.BaseState
import java.time.LocalDate

sealed class OnboardingState(
	override val isLoading: Boolean = false,
	override val throwable: Throwable? = null
) : BaseState {

	data class Quizzes(
		val productName: String? = null,
	) : OnboardingState()

	object Main : OnboardingState()

	data class ProvideDetails(
		val userName: String = "",
		val password: String = "",
		val sex: Sex? = null,
	) : OnboardingState()

	object MoreOpportunities : OnboardingState()

	object KnowledgeFromMasters : OnboardingState()

	data class TellUsAboutInterests(
		override val isLoading: Boolean = false,
		override val throwable: Throwable? = null,
		val allInterests: List<String>? = null,
		val selectedInterests: List<String> = emptyList()
	) : OnboardingState()

	data class HelpYouStay(
		val phoneNumbers: String = "",
		val notificationTimeStart: LocalDate? = null,
		val notificationTimeEnd: LocalDate? = null,
	) : OnboardingState()

	object SpendYourTimeProductively : OnboardingState()
}