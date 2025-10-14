package com.example.tutorplace.ui.screens.onboarding.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState

@Composable
fun OnboardingHelpYouStay(
	state: OnboardingState,
	columnScope: ColumnScope
) = with(columnScope) {
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun OnboardingHelpYouStayPreview() {
	Column {
		OnboardingHelpYouStay(
			columnScope = this,
			state = OnboardingState(
				step = OnboardingState.Step.HELP_YOU_STAY
			)
		)
	}
}