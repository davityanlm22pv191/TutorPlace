package com.example.tutorplace.ui.screens.auth.registration

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
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
import com.example.tutorplace.ui.common.NameTextField
import com.example.tutorplace.ui.common.PasswordTextField
import com.example.tutorplace.ui.common.PhoneTextField
import com.example.tutorplace.ui.common.PurpleButton
import com.example.tutorplace.ui.common.TelegramTextField
import com.example.tutorplace.ui.common.spannabletext.SpanClickableText
import com.example.tutorplace.ui.common.spannabletext.SpanLinkData
import com.example.tutorplace.ui.navigation.Destinations
import com.example.tutorplace.ui.screens.auth.common.AuthSectionDivider
import com.example.tutorplace.ui.common.header.Header
import com.example.tutorplace.ui.common.header.HeaderLogoType
import com.example.tutorplace.ui.screens.auth.common.YandexButton
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationEffect
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationEvent.UI.ConfirmPasswordChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationEvent.UI.EmailChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationEvent.UI.NameChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationEvent.UI.PasswordChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationEvent.UI.PhoneChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationEvent.UI.TelegramChanged
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationState.RegistrationStep.FirstStep
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationState.RegistrationStep.SecondStep
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationViewModel
import com.example.tutorplace.ui.theme.BlackAlpha04
import com.example.tutorplace.ui.theme.PurpleCC
import com.example.tutorplace.ui.theme.ScreenColor
import com.example.tutorplace.ui.theme.Typography

@Composable
fun RegistrationScreen(navController: NavController) {
	val viewModel = hiltViewModel<RegistrationViewModel>()
	val state by viewModel.state.collectAsState()
	val scrollState = rememberScrollState()
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
				title = stringResource(R.string.registration_title),
				description = null,
				onBackButtonClicked = when (state.currentStep) {
					FirstStep::class -> null
					SecondStep::class -> {
						{ viewModel.onFirstStepClicked() }
					}
					else -> null
				}
			)
			AnimatedContent(
				modifier = Modifier.padding(top = 18.dp),
				targetState = state.currentStep,
			) { currentStep ->
				Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
					when (currentStep) {
						FirstStep::class -> {
							NameTextField(
								value = state.firstStep.name,
								label = stringResource(R.string.registration_your_name),
								isError = state.firstStep.isNameError,
								onNextClicked = {},
								onValueChanged = { viewModel.onEvent(NameChanged(it)) }
							)
							PhoneTextField(
								value = state.firstStep.phoneNumber,
								label = stringResource(R.string.registration_your_phone_number),
								isError = state.firstStep.isPhoneNumberError,
								onNextClicked = {},
								onValueChanged = { viewModel.onEvent(PhoneChanged(it)) }
							)
							TelegramTextField(
								value = state.firstStep.telegram,
								label = stringResource(R.string.registration_your_telegram),
								isError = state.firstStep.isTelegramError,
								onDoneClicked = { viewModel.onSecondStepClicked() },
								onValueChanged = { viewModel.onEvent(TelegramChanged(it)) }
							)
						}
						SecondStep::class -> {
							EmailTextField(
								value = state.secondStep.email,
								label = stringResource(R.string.common_auth_your_email),
								isError = state.secondStep.isEmailError,
								onNextClicked = {},
								onValueChanged = { viewModel.onEvent(EmailChanged(it)) }
							)
							PasswordTextField(
								value = state.secondStep.password,
								label = stringResource(R.string.registration_your_password),
								isError = state.secondStep.isPasswordError,
								onDoneClicked = {},
								onValueChanged = { viewModel.onEvent(PasswordChanged(it)) }
							)
							PasswordTextField(
								value = state.secondStep.confirmPassword,
								label = stringResource(R.string.registration_repeat_password),
								isError = state.secondStep.isConfirmPasswordError,
								onDoneClicked = {},
								onValueChanged = { viewModel.onEvent(ConfirmPasswordChanged(it)) }
							)
						}
						else -> {}
					}
				}
			}

			Spacer(Modifier.weight(1f))
			PurpleButton(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 16.dp),
				text = when (state.currentStep) {
					FirstStep::class -> stringResource(R.string.common_next)
					SecondStep::class -> stringResource(R.string.registration_registration)
					else -> return@Column
				},
				isLoading = state.isLoading,
				isEnabled = true,
			) {
				if (state.isLoading) return@PurpleButton
				when (state.currentStep) {
					FirstStep::class -> viewModel.onSecondStepClicked()
					SecondStep::class -> viewModel.onRegisterClicked()
					else -> return@PurpleButton
				}
			}
			AuthSectionDivider(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 8.dp)
			)
			YandexButton(modifier = Modifier.padding(top = 8.dp)) {
				viewModel.onYandexClicked()
			}
			SpanClickableText(
				modifier = Modifier
					.padding(top = 12.dp)
					.fillMaxWidth(),
				text = stringResource(R.string.registration_offer_and_terms),
				links = listOf(
					SpanLinkData(
						link = stringResource(R.string.registration_offer_span),
						tag = "OFFER",
						style = SpanStyle(color = PurpleCC),
						onClick = { viewModel.onOfferClicked() },
					),
					SpanLinkData(
						link = stringResource(R.string.registration_terms_span),
						tag = "TERMS",
						style = SpanStyle(color = PurpleCC),
						onClick = { viewModel.onTermsClicked() },
					)
				),
				textStyle = Typography.labelMedium.copy(textAlign = TextAlign.Center),
			)
			HorizontalDivider(
				modifier = Modifier
					.padding(top = 16.dp)
					.fillMaxWidth(),
				thickness = 1.dp,
				color = BlackAlpha04
			)
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
						onClick = { navController.popBackStack() }
					)
				),
				textStyle = Typography.labelMedium.copy(textAlign = TextAlign.Center)
			)
		}
	}
}

@Composable
private fun ObserveViewModelEvents(
	viewModel: RegistrationViewModel,
	navController: NavController
) {
	LaunchedEffect(Unit) {
		viewModel.effect.collect { effect ->
			when (effect) {
				RegistrationEffect.NavigateToHome -> navController.navigate(
					Destinations.Home(
						Destinations.Home.HomeParams(isShouldShowOnboarding = true)
					).route
				) {
					popUpTo(Destinations.AuthorizationFlow.FLOW_ROUTE) { inclusive = true }
				}
			}
		}
	}
}

@Preview
@Composable
private fun RegistrationScreenPreview() {
	RegistrationScreen(rememberNavController())
}