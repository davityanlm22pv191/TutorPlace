package com.example.tutorplace.ui.screens.auth.restorepassword

import androidx.compose.animation.AnimatedContent
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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tutorplace.R
import com.example.tutorplace.helpers.FormatHelper
import com.example.tutorplace.ui.common.PurpleButton
import com.example.tutorplace.ui.common.header.Header
import com.example.tutorplace.ui.common.header.HeaderLogoType
import com.example.tutorplace.ui.common.spannabletext.SpanClickableText
import com.example.tutorplace.ui.common.spannabletext.SpanLinkData
import com.example.tutorplace.ui.common.textfield.EmailTextField
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordEffect.NavigateToAuthorization
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordEvent.EmailChanged
import com.example.tutorplace.ui.screens.auth.restorepassword.presentation.RestorePasswordViewModel
import com.example.tutorplace.ui.theme.BlackAlpha04
import com.example.tutorplace.ui.theme.PurpleCC
import com.example.tutorplace.ui.theme.ScreenColor
import com.example.tutorplace.ui.theme.TutorPlaceTheme
import com.example.tutorplace.ui.theme.Typography

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
			containerColor = ScreenColor
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
					logo = HeaderLogoType.Image(R.drawable.ic_tutor_place_lettering_logo),
					title = stringResource(R.string.restore_password_title),
					description = if (state.isEmailSent) {
						stringResource(
							R.string.restore_password_description_with_email_format, state.email
						)
					} else {
						stringResource(R.string.restore_password_description)
					},
					onBackButtonClicked = { viewModel.backClicked() }
				)
				AnimatedContent(targetState = state.isEmailSent) { isEmailSent ->
					if (!isEmailSent) {
						EmailTextField(
							modifier = Modifier
								.fillMaxWidth()
								.padding(top = 18.dp),
							value = state.email,
							label = stringResource(R.string.common_auth_your_email),
							isError = state.isEmailError,
							onNextClicked = { focusManager.clearFocus() },
							onValueChanged = { email -> viewModel.onEvent(EmailChanged(email)) }
						)
					}
				}

				Spacer(Modifier.weight(1f))
				PurpleButton(
					modifier = Modifier.fillMaxWidth(),
					text = when {
						state.timerInSeconds != 0 -> FormatHelper.formatTime(state.timerInSeconds)
						state.timerInSeconds == 0 && state.isEmailSent -> stringResource(R.string.restore_password_retry_send_button)
						else -> stringResource(R.string.restore_password_restore_password)
					},
					isLoading = state.isLoading,
					isEnabled = state.timerInSeconds == 0,
				) {
					when {
						state.isLoading -> Unit
						!state.isEmailSent -> viewModel.restoreClicked()
						state.isEmailSent -> viewModel.retrySendClicked()
					}
				}

				HorizontalDivider(
					modifier = Modifier
						.padding(top = 16.dp)
						.fillMaxWidth(),
					thickness = 1.dp,
					color = BlackAlpha04
				)
				DoYouHaveAnAccountSection { viewModel.authorizeClicked() }
			}
		}
	}
}

@Composable
private fun DoYouHaveAnAccountSection(onAuthorizedClicked: () -> Unit) {
	SpanClickableText(
		modifier = Modifier
			.padding(top = 16.dp)
			.fillMaxWidth(),
		text = stringResource(R.string.common_auth_already_have_account),
		links = listOf(
			SpanLinkData(
				link = stringResource(R.string.restore_password_already_have_account_spannable),
				tag = "ENTRY",
				style = SpanStyle(color = PurpleCC),
				onClick = { onAuthorizedClicked() }
			)
		),
		textStyle = Typography.labelMedium.copy(textAlign = TextAlign.Center)
	)
}

@Composable
private fun ObserveViewModelEvent(
	viewModel: RestorePasswordViewModel,
	navController: NavController
) {
	LaunchedEffect(Unit) {
		viewModel.effect.collect { effect ->
			when (effect) {
				is NavigateToAuthorization -> navController.popBackStack()
			}
		}
	}
}

@Preview
@Composable
private fun RestorePasswordScreenPreview() {
	RestorePasswordScreen(rememberNavController())
}