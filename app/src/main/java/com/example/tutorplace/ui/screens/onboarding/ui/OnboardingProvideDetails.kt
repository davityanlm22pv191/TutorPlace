package com.example.tutorplace.ui.screens.onboarding.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState

@Composable
fun OnboardingProvideDetails(
	state: OnboardingState.ProvideDetails,
	columnScope: ColumnScope,
) = with(columnScope) {
}

@Preview(showSystemUi = false, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun OnboardingProvideDetailsPreview() {
	Column {
		OnboardingProvideDetails(
			columnScope = this,
			state = OnboardingState.ProvideDetails()
		)
	}
}