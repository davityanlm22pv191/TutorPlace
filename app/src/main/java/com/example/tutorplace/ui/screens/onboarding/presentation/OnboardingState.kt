package com.example.tutorplace.ui.screens.onboarding.presentation

import com.example.tutorplace.domain.model.Sex
import com.example.tutorplace.ui.base.BaseState
import java.time.LocalDate

sealed class OnboardingState(
	override val isLoading: Boolean = true,
	override val throwable: Throwable? = null,
	open val isBackButtonVisible: Boolean,
) : BaseState {

	data class Quizzes(
		val productName: String? = "Астрология",
		override val isLoading: Boolean = true,
		override val throwable: Throwable? = null
	) : OnboardingState(isBackButtonVisible = false)

	object Main : OnboardingState(isBackButtonVisible = false)

	data class ProvideDetails(
		val userName: String = "",
		val password: String = "",
		val sex: Sex? = null,
	) : OnboardingState(isBackButtonVisible = false)

	object MoreOpportunities : OnboardingState(isBackButtonVisible = true)

	object KnowledgeFromMasters : OnboardingState(isBackButtonVisible = true)

	data class TellUsAboutInterests(
		override val isLoading: Boolean = false,
		override val throwable: Throwable? = null,
		val allInterests: List<String>? = null,
		val selectedInterests: List<String> = emptyList()
	) : OnboardingState(isBackButtonVisible = true)

	data class HelpYouStay(
		val phoneNumbers: String = "",
		val notificationTimeStart: LocalDate? = null,
		val notificationTimeEnd: LocalDate? = null,
	) : OnboardingState(isBackButtonVisible = true)

	object SpendYourTimeProductively : OnboardingState(isBackButtonVisible = true)
}