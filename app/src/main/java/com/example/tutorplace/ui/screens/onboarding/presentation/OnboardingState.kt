package com.example.tutorplace.ui.screens.onboarding.presentation

import androidx.annotation.StringRes
import com.example.tutorplace.R
import com.example.tutorplace.data.onboarding.model.OnboardingInfo
import com.example.tutorplace.domain.model.DataInfo
import com.example.tutorplace.domain.model.Sex
import com.example.tutorplace.ui.base.BaseState
import com.example.tutorplace.ui.common.TextFieldState

data class OnboardingState(
	val onboardingInfo: DataInfo<OnboardingInfo> = DataInfo(OnboardingInfo.empty(), isLoading = true),
	val step: Step,
	val userName: TextFieldState = TextFieldState(),
	val password: TextFieldState = TextFieldState(),
	val repeatedPassword: TextFieldState = TextFieldState(),
	val sex: Sex? = null,
	val selectedInterestsIds: List<Int> = emptyList(),
	val phoneNumber: TextFieldState = TextFieldState(),
	val notificationStartTime: String? = null,
	val notificationEndTime: String? = null,
	val isSexError: Boolean = false,
	val isBackButtonVisible: Boolean = false,
	val isMainButtonEnabled: Boolean = true,
	@param:StringRes val mainButtonTitle: Int = R.string.onboarding_next_step,
	val isSkipButtonVisible: Boolean = false,
) : BaseState {

	enum class Step {
		CONGRATULATIONS,
		WELCOME,
		PROVIDE_DETAILS,
		MORE_OPPORTUNITIES,
		KNOWLEDGE_FROM_MASTERS,
		TELL_US_ABOUT_INTERESTS,
		HELP_YOU_STAY,
		SPEND_YOUR_TIME_PRODUCTIVELY
	}
}