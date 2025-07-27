package com.example.tutorplace.ui.screens.auth.restorepassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection.Ltr
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tutorplace.R
import com.example.tutorplace.ui.common.EmailTextField
import com.example.tutorplace.ui.common.PurpleButton
import com.example.tutorplace.ui.common.spannabletext.SpanClickableText
import com.example.tutorplace.ui.common.spannabletext.SpanLinkData
import com.example.tutorplace.ui.screens.auth.common.Header
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordCommand
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordCommand.EmailChanged
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordEvent.OnAuthorization
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordViewModel
import com.example.tutorplace.ui.theme.BlackAlpha04
import com.example.tutorplace.ui.theme.PurpleCC
import com.example.tutorplace.ui.theme.TutorPlaceTheme
import com.example.tutorplace.ui.theme.Typography
import com.example.tutorplace.ui.theme.White

@Composable
fun RestorePasswordScreen(navController: NavController) {
	val viewModel = hiltViewModel<RestorePasswordViewModel>()
	val state by viewModel.state.collectAsState()
	val scrollState = rememberScrollState()
	val focusManager = LocalFocusManager.current
	ObserveViewModelEvent(viewModel, navController)
	TutorPlaceTheme {
		Scaffold(
			modifier = Modifier.fillMaxSize(),
			containerColor = White
		) { paddingValues ->
			Column(
				modifier = Modifier
					.fillMaxSize()
					.padding(
						top = paddingValues.calculateTopPadding(),
						start = paddingValues.calculateStartPadding(Ltr) + 20.dp,
						end = paddingValues.calculateEndPadding(Ltr) + 20.dp,
						bottom = paddingValues.calculateBottomPadding() + 16.dp
					)
					.verticalScroll(scrollState)
					.imePadding(),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Header(
					title = R.string.restore_password_title,
					description = R.string.restore_password_description,
					onBackButtonClicked = {
						viewModel.handleCommand(RestorePasswordCommand.BackClicked)
					}
				)
				EmailTextField(
					modifier = Modifier
						.fillMaxWidth()
						.padding(top = 18.dp),
					value = state.email,
					label = stringResource(R.string.common_your_email),
					isError = state.isEmailError,
					onNextClicked = { focusManager.clearFocus() },
					onValueChanged = { email -> viewModel.handleCommand(EmailChanged(email)) }
				)
				Spacer(Modifier.weight(1f))
				PurpleButton(
					modifier = Modifier.fillMaxWidth(),
					text = stringResource(R.string.restore_password_restore_password),
					isLoading = state.isLoading,
					isEnabled = state.isRestoreButtonEnabled,
				) {

				}
				HorizontalDivider(
					modifier = Modifier
						.padding(top = 16.dp)
						.fillMaxWidth(), thickness = 1.dp, color = BlackAlpha04
				)
				SpanClickableText(
					modifier = Modifier
						.padding(top = 16.dp)
						.fillMaxWidth(),
					text = stringResource(R.string.restore_password_already_have_account),
					links = listOf(
						SpanLinkData(
							link = stringResource(R.string.restore_password_already_have_account_spannable),
							tag = "ENTRY",
							style = SpanStyle(color = PurpleCC),
							onClick = {
								viewModel.handleCommand(RestorePasswordCommand.AuthorizeClicked)
							}
						)
					),
					textStyle = Typography.labelMedium.copy(textAlign = TextAlign.Center)
				)
			}
		}
	}
}

@Composable
private fun ObserveViewModelEvent(
	viewModel: RestorePasswordViewModel,
	navController: NavController
) {
	LaunchedEffect(Unit) {
		viewModel.event.collect { event ->
			when (event) {
				is OnAuthorization -> navController.popBackStack()
			}
		}
	}
}

@Preview
@Composable
private fun RestorePasswordScreenPreview() {
	RestorePasswordScreen(rememberNavController())
}