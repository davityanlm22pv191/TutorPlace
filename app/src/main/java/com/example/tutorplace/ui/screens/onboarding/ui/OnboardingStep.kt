package com.example.tutorplace.ui.screens.onboarding.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.example.tutorplace.ui.common.header.HeaderLogoType

sealed class OnboardingStep(
	open val logo: HeaderLogoType,
	@param:StringRes open val titleResId: Int,
	@param:StringRes open val descriptionResId: Int?,
	open val content: @Composable () -> Unit,
) {

}