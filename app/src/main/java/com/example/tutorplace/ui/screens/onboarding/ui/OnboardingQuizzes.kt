package com.example.tutorplace.ui.screens.onboarding.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState

@Composable
fun OnboardingQuizzes(
	state: OnboardingState.Quizzes,
	columnScope: ColumnScope,
) = with(columnScope) {

}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun OnboardingQuizzesPreview() {
	Column {
		OnboardingQuizzes(
			columnScope = this,
			state = OnboardingState.Quizzes(
				productName = "Tutor Place"
			)
		)
	}
}