package com.example.tutorplace.ui.screens.onboarding.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorplace.R
import com.example.tutorplace.ui.common.PhoneTextField
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState
import com.example.tutorplace.ui.theme.Black16
import com.example.tutorplace.ui.theme.Typography

@Composable
fun OnboardingHelpYouStay(
	state: OnboardingState,
	columnScope: ColumnScope,
	onPhoneNumberChanged: (String) -> Unit
) = with(columnScope) {
	PhoneTextField(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp),
		value = state.phoneNumber.value,
		label = stringResource(R.string.common_phone_number),
		isError = state.phoneNumber.isError,
		onNextClicked = {},
		onValueChanged = { phone -> onPhoneNumberChanged(phone) }
	)
	Text(
		modifier = Modifier
			.padding(top = 16.dp)
			.padding(horizontal = 16.dp),
		text = stringResource(R.string.onboarding_what_time_you_want_receive_notifications),
		style = Typography.labelMedium.copy(color = Black16)
	)
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun OnboardingHelpYouStayPreview() {
	Column {
		OnboardingHelpYouStay(
			columnScope = this,
			state = OnboardingState(
				step = OnboardingState.Step.HELP_YOU_STAY,
			),
			onPhoneNumberChanged = {}
		)
	}
}