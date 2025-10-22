package com.example.tutorplace.ui.screens.onboarding.ui.uistate

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.example.tutorplace.ui.common.header.HeaderLogoType
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingViewModel

data class OnboardingUiState(
	val header: HeaderLogoType,
	@param:StringRes val title: Int,
	@param:StringRes val description: Int?,
	val contentSeparatorHeightDp: Dp,
	@param:StringRes val mainButtonTitle: Int,
	val isBackButtonVisible: Boolean,
	val isSkipButtonVisible: Boolean,
	val content: @Composable ColumnScope.() -> Unit
) {
	companion object {
		operator fun invoke(
			state: OnboardingState,
			viewModel: OnboardingViewModel
		): OnboardingUiState {
			return OnboardingUiFactory.create(state, viewModel)
		}
	}
}