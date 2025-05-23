package com.example.tutorplace.ui.screens.auth.authorization

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
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
import com.example.tutorplace.ui.common.AuthSectionDivider
import com.example.tutorplace.ui.common.EmailTextField
import com.example.tutorplace.ui.common.PasswordTextField
import com.example.tutorplace.ui.common.PurpleButton
import com.example.tutorplace.ui.common.spannabletext.SpanClickableText
import com.example.tutorplace.ui.common.spannabletext.SpanLinkData
import com.example.tutorplace.ui.navigation.Destinations
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationCommand
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.OnHome
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.OnRegistration
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.OnRestorePassword
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent.onSupport
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationViewModel
import com.example.tutorplace.ui.theme.Black16
import com.example.tutorplace.ui.theme.GreyF8
import com.example.tutorplace.ui.theme.PurpleCC
import com.example.tutorplace.ui.theme.TutorPlaceTheme
import com.example.tutorplace.ui.theme.Typography
import com.example.tutorplace.ui.theme.White

@Composable
fun AuthorizationScreen(navController: NavController) = TutorPlaceTheme {
	val viewModel = hiltViewModel<AuthorizationViewModel>()
	val state by viewModel.state.collectAsState()
	val scrollState = rememberScrollState()
	val focusManager = LocalFocusManager.current
	ObserveViewModelEvents(viewModel, navController)
	Scaffold(
		modifier = Modifier.fillMaxSize(),
		containerColor = White
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
			Image(
				modifier = Modifier.padding(top = 44.dp),
				painter = painterResource(R.drawable.ic_tutor_place_logo),
				contentDescription = null
			)
			Text(
				modifier = Modifier.padding(top = 24.dp),
				text = stringResource(R.string.authorization_enter_to_profile),
				style = Typography.headlineLarge,
				color = Black16
			)

			EmailTextField(
				modifier = Modifier
					.padding(top = 18.dp)
					.padding(horizontal = 20.dp),
				value = state.email,
				label = stringResource(R.string.authorization_your_email),
				isError = state.isEmailError,
				onNextClicked = { focusManager.moveFocus(FocusDirection.Next) }
			) { viewModel.handleCommand(AuthorizationCommand.EmailChanged(it)) }

			PasswordTextField(
				modifier = Modifier
					.padding(top = 6.dp)
					.padding(horizontal = 20.dp),
				value = state.password,
				label = stringResource(R.string.authorization_your_password),
				isError = state.isPasswordError,
				onDoneClicked = { viewModel.handleCommand(AuthorizationCommand.AuthorizeClicked) }
			) { viewModel.handleCommand(AuthorizationCommand.PasswordChanged(it)) }

			TextButton(
				modifier = Modifier
					.align(Alignment.Start)
					.padding(start = 10.dp),
				colors = ButtonDefaults.textButtonColors(contentColor = PurpleCC),
				onClick = { viewModel.handleCommand(AuthorizationCommand.RestoreClicked) },
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
				isEnabled = state.isLoginButtonEnabled
			) { viewModel.handleCommand(AuthorizationCommand.AuthorizeClicked) }
			AuthSectionDivider(
				modifier = Modifier
					.padding(top = 8.dp)
					.padding(horizontal = 20.dp)
			)
			Button(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 8.dp)
					.padding(horizontal = 20.dp)
					.height(50.dp),
				shape = RoundedCornerShape(12.dp),
				colors = ButtonDefaults.buttonColors().copy(containerColor = GreyF8),
				onClick = { viewModel.handleCommand(AuthorizationCommand.YandexClicked) }
			) {
				Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
					Image(
						modifier = Modifier.align(Alignment.CenterStart),
						painter = painterResource(R.drawable.ic_yandex_logo),
						contentDescription = null
					)
					Text(
						text = stringResource(R.string.authorization_entry_by_yandex_id),
						style = Typography.bodyLarge,
						color = Black16
					)
				}
			}
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
						onClick = { viewModel.handleCommand(AuthorizationCommand.SupportClicked) }
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
						onClick = { viewModel.handleCommand(AuthorizationCommand.RegistrationClicked) }
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
		viewModel.event.collect { event ->
			when (event) {
				is OnHome -> navController.navigate(Destinations.Home.route)
				is OnRestorePassword -> navController.navigate(Destinations.Home.route) // TODO THIS IS MOCK ROUTE
				is OnRegistration -> navController.navigate(Destinations.Home.route) // TODO THIS IS MOCK ROUTE
				is onSupport -> navController.navigate(Destinations.Home.route) // TODO THIS IS MOCK ROUTE
			}
		}
	}
}

@Preview
@Composable
private fun AuthorizationScreenPreview() = AuthorizationScreen(rememberNavController())