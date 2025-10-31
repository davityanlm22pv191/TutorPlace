package com.example.tutorplace.ui.screens.auth.authorization

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection.Ltr
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.tutorplace.R
import com.example.tutorplace.navigation.Destinations
import com.example.tutorplace.navigation.Destinations.MainScreen.MainScreenParams
import com.example.tutorplace.ui.common.PurpleButton
import com.example.tutorplace.ui.common.header.Header
import com.example.tutorplace.ui.common.header.HeaderLogoType
import com.example.tutorplace.ui.common.spannabletext.SpanClickableText
import com.example.tutorplace.ui.common.spannabletext.SpanLinkData
import com.example.tutorplace.ui.common.textfield.EmailTextField
import com.example.tutorplace.ui.common.textfield.PasswordTextField
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToHome
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToRegistration
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToRestorePassword
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToSupport
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.EmailChanged
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.PasswordChanged
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationState
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationViewModel
import com.example.tutorplace.ui.common.AuthSectionDivider
import com.example.tutorplace.ui.common.YandexButton
import com.example.tutorplace.ui.theme.PurpleCC
import com.example.tutorplace.ui.theme.ScreenColor
import com.example.tutorplace.ui.theme.Typography

@Composable
fun AuthorizationScreen(navController: NavHostController) {
	val viewModel = hiltViewModel<AuthorizationViewModel>()
	val state by viewModel.state.collectAsState()
	ObserveViewModelEvents(viewModel, navController)
	AuthorizationScreen(
		state,
		onEmailChanged = { email -> viewModel.onEvent(EmailChanged(email)) },
		onPasswordChanged = { password -> viewModel.onEvent(PasswordChanged(password)) },
		onRestoreClicked = { viewModel.onRestoreClicked() },
		onEnterClicked = { viewModel.onEnterClicked() },
		onYandexClicked = { viewModel.onYandexClicked() },
		onSupportClicked = { viewModel.onSupportClicked() },
		onRegistrationClicked = { viewModel.onRegistrationClicked() }
	)
}

@Composable
private fun AuthorizationScreen(
	state: AuthorizationState,
	onEmailChanged: (email: String) -> Unit,
	onPasswordChanged: (password: String) -> Unit,
	onRestoreClicked: () -> Unit,
	onEnterClicked: () -> Unit,
	onYandexClicked: () -> Unit,
	onSupportClicked: () -> Unit,
	onRegistrationClicked: () -> Unit,
) {
	val emailFocusRequester = remember { FocusRequester() }
	val passwordFocusRequester = remember { FocusRequester() }
	val scrollState = rememberScrollState()
	Scaffold(
		modifier = Modifier.fillMaxSize(),
		containerColor = ScreenColor
	) { paddingValues ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(
					top = paddingValues.calculateTopPadding(),
					start = paddingValues.calculateStartPadding(Ltr),
					end = paddingValues.calculateEndPadding(Ltr),
					bottom = paddingValues.calculateBottomPadding() + 16.dp
				)
				.verticalScroll(scrollState)
				.windowInsetsPadding(WindowInsets.ime),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Header(
				logo = HeaderLogoType.Image(R.drawable.ic_tutor_place_lettering_logo),
				title = stringResource(R.string.authorization_enter_to_profile),
				description = null,
				onBackButtonClicked = null,
			)
			EmailTextField(
				modifier = Modifier
					.padding(top = 18.dp)
					.padding(horizontal = 20.dp)
					.focusRequester(emailFocusRequester),
				value = state.email,
				label = stringResource(R.string.common_auth_your_email),
				isError = state.isEmailError,
				onNextClicked = { passwordFocusRequester.requestFocus() },
				onValueChanged = { value -> onEmailChanged(value) }
			)
			PasswordTextField(
				modifier = Modifier
					.padding(top = 6.dp)
					.padding(horizontal = 20.dp)
					.focusRequester(passwordFocusRequester),
				value = state.password,
				label = stringResource(R.string.authorization_your_password),
				isError = state.isPasswordError,
				onDoneClicked = { onEnterClicked() },
				onValueChanged = { value -> onPasswordChanged(value) }
			)
			TextButton(
				modifier = Modifier
					.align(Alignment.Start)
					.padding(start = 10.dp),
				colors = ButtonDefaults.textButtonColors(contentColor = PurpleCC),
				onClick = { onRestoreClicked() },
			) {
				Text(
					modifier = Modifier.padding(top = 6.dp),
					text = stringResource(R.string.authorization_restore_password),
					style = Typography.bodyMedium,
				)
			}
			Spacer(Modifier.weight(1f))
			PurpleButton(
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 20.dp),
				text = stringResource(R.string.authorization_entry),
				isLoading = state.isLoading,
				isEnabled = true
			) {
				if (!state.isLoading) {
					onEnterClicked()
				}
			}
			AuthSectionDivider(
				modifier = Modifier
					.padding(top = 8.dp)
					.padding(horizontal = 20.dp)
			)
			YandexButton(
				modifier = Modifier
					.padding(top = 8.dp)
					.padding(horizontal = 20.dp),
				onClick = { onYandexClicked() }
			)
			SupportSection(
				onSupportClick = { onSupportClicked() },
				onRegisterClick = { onRegistrationClicked() }
			)
		}
	}
}

@Composable
private fun SupportSection(onSupportClick: () -> Unit, onRegisterClick: () -> Unit) {
	Column(
		modifier = Modifier
			.padding(horizontal = 20.dp)
			.fillMaxWidth(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		SpanClickableText(
			modifier = Modifier.padding(top = 12.dp),
			text = stringResource(R.string.authorization_email_error_support_hint),
			links = listOf(
				SpanLinkData(
					link = stringResource(R.string.authorization_email_error_support_hint_spannable),
					tag = "SUPPORT",
					SpanStyle(color = PurpleCC),
					onClick = onSupportClick
				)
			),
			textStyle = Typography.labelMedium.copy(textAlign = TextAlign.Center)
		)

		SpanClickableText(
			modifier = Modifier.padding(top = 32.dp),
			text = stringResource(R.string.authorization_not_yet_account),
			links = listOf(
				SpanLinkData(
					link = stringResource(R.string.auth_register),
					tag = "REGISTRATION",
					SpanStyle(color = PurpleCC),
					onClick = onRegisterClick
				)
			),
			textStyle = Typography.labelMedium.copy(textAlign = TextAlign.Center)
		)
	}
}

@Composable
private fun ObserveViewModelEvents(
	viewModel: AuthorizationViewModel,
	navController: NavController
) {
	LaunchedEffect(Unit) {
		viewModel.effect.collect { effect ->
			when (effect) {
				is NavigateToHome -> navController.navigate(
					Destinations.MainScreen(MainScreenParams(isShouldShowOnboarding = true)).route
				) {
					popUpTo(Destinations.AuthorizationFlow.FLOW_ROUTE) {
						inclusive = true
					}
				}
				is NavigateToRestorePassword -> navController.navigate(Destinations.AuthorizationFlow.RestorePassword.route)
				is NavigateToRegistration -> navController.navigate(Destinations.AuthorizationFlow.Registration.route)
				is NavigateToSupport -> Unit
			}
		}
	}
}

@Preview
@Composable
private fun AuthorizationScreenPreview() {
	AuthorizationScreen(
		state = AuthorizationState(),
		onEmailChanged = {},
		onPasswordChanged = {},
		onRestoreClicked = {},
		onEnterClicked = {},
		onYandexClicked = {},
		onSupportClicked = {},
		onRegistrationClicked = {}
	)
}