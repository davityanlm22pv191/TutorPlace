package com.example.tutorplace.ui.screens.onboarding.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorplace.R
import com.example.tutorplace.data.onboarding.model.OnboardingInfo
import com.example.tutorplace.domain.model.DataInfo
import com.example.tutorplace.domain.model.Sex
import com.example.tutorplace.ui.common.NameTextField
import com.example.tutorplace.ui.common.PasswordTextField
import com.example.tutorplace.ui.common.TextFieldState
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState

@Composable
fun OnboardingProvideDetails(
	state: OnboardingState,
	columnScope: ColumnScope,
	onUserNameChanged: (String) -> Unit,
	onPasswordChanged: (String) -> Unit,
	onRepeatedPasswordChanged: (String) -> Unit,
	onSexChosen: (Sex) -> Unit,
) = with(columnScope) {
	NameTextField(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp),
		label = stringResource(R.string.common_user_name),
		value = state.userName.value,
		isError = state.userName.isError,
		onNextClicked = {},
		onValueChanged = { onUserNameChanged(it) },
	)
	PasswordTextField(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp)
			.padding(top = 6.dp),
		value = state.password.value,
		label = stringResource(R.string.common_password),
		isError = state.password.isError,
		onDoneClicked = {},
		onValueChanged = { onPasswordChanged(it) }
	)
	PasswordTextField(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp)
			.padding(top = 6.dp),
		value = state.repeatedPassword.value,
		label = stringResource(R.string.common_repeat_password),
		isError = state.repeatedPassword.isError,
		onDoneClicked = {},
		onValueChanged = { onRepeatedPasswordChanged(it) }
	)
}

@Preview(showSystemUi = false, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun OnboardingProvideDetailsPreview() {
	Column {
		OnboardingProvideDetails(
			columnScope = this,
			state = OnboardingState(
				onboardingInfo = DataInfo(OnboardingInfo.empty()),
				step = OnboardingState.Step.PROVIDE_DETAILS,
				password = TextFieldState(value = "123456")
			),
			onUserNameChanged = {},
			onPasswordChanged = {},
			onRepeatedPasswordChanged = {},
			onSexChosen = {}
		)
	}
}