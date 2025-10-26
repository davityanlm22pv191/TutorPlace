package com.example.tutorplace.ui.screens.auth.authorization

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tutorplace.R
import com.example.tutorplace.ui.common.textfield.EmailTextField
import com.example.tutorplace.ui.common.textfield.PasswordTextField
import com.example.tutorplace.ui.common.PurpleButton
import com.example.tutorplace.ui.common.header.Header
import com.example.tutorplace.ui.common.header.HeaderLogoType
import com.example.tutorplace.ui.common.spannabletext.SpanClickableText
import com.example.tutorplace.ui.common.spannabletext.SpanLinkData
import com.example.tutorplace.navigation.Destinations
import com.example.tutorplace.navigation.Destinations.MainScreen.MainScreenParams
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToHome
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToRegistration
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToRestorePassword
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEffect.NavigateToSupport
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationViewModel
import com.example.tutorplace.ui.screens.auth.common.AuthSectionDivider
import com.example.tutorplace.ui.screens.auth.common.YandexButton
import com.example.tutorplace.ui.theme.PurpleCC
import com.example.tutorplace.ui.theme.ScreenColor
import com.example.tutorplace.ui.theme.TutorPlaceTheme
import com.example.tutorplace.ui.theme.Typography

@Composable
fun AuthorizationScreen(navController: NavController) = TutorPlaceTheme {
	val viewModel = hiltViewModel<AuthorizationViewModel>()
	val state by viewModel.state.collectAsState()
	val scrollState = rememberScrollState()
	val emailFocusRequester = remember { FocusRequester() }
	val passwordFocusRequester = remember { FocusRequester() }
	ObserveViewModelEvents(viewModel, navController)
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
				.imePadding(),
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
				onNextClicked = { passwordFocusRequester.requestFocus() }
			) { viewModel.onEvent(AuthorizationEvent.EmailChanged(it)) }

			PasswordTextField(
				modifier = Modifier
					.padding(top = 6.dp)
					.padding(horizontal = 20.dp)
					.focusRequester(passwordFocusRequester),
				value = state.password,
				label = stringResource(R.string.authorization_your_password),
				isError = state.isPasswordError,
				onDoneClicked = { viewModel.onEnterClicked() }
			) { viewModel.onEvent(AuthorizationEvent.PasswordChanged(it)) }

			TextButton(
				modifier = Modifier
					.align(Alignment.Start)
					.padding(start = 10.dp),
				colors = ButtonDefaults.textButtonColors(contentColor = PurpleCC),
				onClick = { viewModel.onRestoreClicked() },
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
					viewModel.onEnterClicked()
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
					.padding(horizontal = 20.dp)
			) { viewModel.onYandexClicked() }
			SpanClickableText(
				modifier = Modifier
					.padding(top = 12.dp)
					.padding(horizontal = 20.dp),
				text = stringResource(R.string.authorization_email_error_support_hint),
				links = listOf(
					SpanLinkData(
						link = stringResource(R.string.authorization_email_error_support_hint_spannable),
						tag = "SUPPORT",
						SpanStyle(color = PurpleCC),
						onClick = { viewModel.onSupportClicked() }
					)
				),
				textStyle = Typography.labelMedium.copy(textAlign = TextAlign.Center)
			)

			SpanClickableText(
				modifier = Modifier
					.padding(top = 32.dp)
					.padding(horizontal = 20.dp)
					.fillMaxWidth(),
				text = stringResource(R.string.authorization_not_yet_account),
				links = listOf(
					SpanLinkData(
						link = stringResource(R.string.auth_register),
						tag = "REGISTRATION",
						SpanStyle(color = PurpleCC),
						onClick = { viewModel.onRegistrationClicked() }
					)
				),
				textStyle = Typography.labelMedium.copy(textAlign = TextAlign.Center),
			)
		}
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
					Destinations.MainScreen(MainScreenParams(isShouldShowOnboarding = false)).route
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
private fun AuthorizationScreenPreview() = AuthorizationScreen(rememberNavController())