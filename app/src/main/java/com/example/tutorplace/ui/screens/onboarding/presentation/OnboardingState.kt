package com.example.tutorplace.ui.screens.onboarding.presentation

import androidx.annotation.StringRes
import com.example.tutorplace.R
import com.example.tutorplace.domain.model.DataInfo
import com.example.tutorplace.domain.model.Sex
import com.example.tutorplace.ui.base.BaseState
import java.time.LocalDate

sealed class OnboardingState(
	override val isLoading: Boolean = false,
	override val throwable: Throwable? = null,
	open val isBackButtonVisible: Boolean,
	open val isMainButtonEnabled: Boolean,
	@param:StringRes open val mainButtonTitle: Int = R.string.onboarding_next_step,
	open val isSkipButtonVisible: Boolean = false,
) : BaseState {

	data class Quizzes(
		val productName: DataInfo<String> = DataInfo(data = ""),
		override val isLoading: Boolean = false,
		override val throwable: Throwable? = null
	) : OnboardingState(
		isBackButtonVisible = false,
		isMainButtonEnabled = true,
	)

	object Main : OnboardingState(
		isBackButtonVisible = false,
		isMainButtonEnabled = true,
	)

	data class ProvideDetails(
		val userName: String = "",
		val password: String = "",
		val sex: Sex? = null,
	) : OnboardingState(
		isBackButtonVisible = false,
		isMainButtonEnabled = false,
	)

	object MoreOpportunities : OnboardingState(
		isBackButtonVisible = true,
		isMainButtonEnabled = true,
	)

	object KnowledgeFromMasters : OnboardingState(
		isBackButtonVisible = true,
		isMainButtonEnabled = true,
	)

	data class TellUsAboutInterests(
		override val isLoading: Boolean = false,
		override val throwable: Throwable? = null,
		val allInterests: List<String>? = null,
		val selectedInterests: List<String> = emptyList()
	) : OnboardingState(
		isBackButtonVisible = true,
		isMainButtonEnabled = false,
	)

	data class HelpYouStay(
		val phoneNumbers: String = "",
		val notificationTimeStart: LocalDate? = null,
		val notificationTimeEnd: LocalDate? = null,
	) : OnboardingState(
		isBackButtonVisible = true,
		isMainButtonEnabled = false,
		mainButtonTitle = R.string.onboarding_bind,
		isSkipButtonVisible = true,
	)

	object SpendYourTimeProductively : OnboardingState(
		isBackButtonVisible = true,
		mainButtonTitle = R.string.onboarding_start_learning,
		isMainButtonEnabled = true,
	)
}