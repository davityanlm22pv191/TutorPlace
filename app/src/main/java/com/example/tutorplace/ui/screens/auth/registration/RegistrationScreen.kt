package com.example.tutorplace.ui.screens.auth.registration

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
import com.example.tutorplace.ui.common.PurpleButton
import com.example.tutorplace.ui.common.spannabletext.SpanClickableText
import com.example.tutorplace.ui.common.spannabletext.SpanLinkData
import com.example.tutorplace.ui.screens.auth.common.AuthSectionDivider
import com.example.tutorplace.ui.screens.auth.common.Header
import com.example.tutorplace.ui.screens.auth.common.YandexButton
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationState.RegistrationStep.FirstStep
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationState.RegistrationStep.SecondStep
import com.example.tutorplace.ui.screens.auth.registration.presentation.RegistrationViewModel
import com.example.tutorplace.ui.theme.BlackAlpha04
import com.example.tutorplace.ui.theme.PurpleCC
import com.example.tutorplace.ui.theme.Typography
import com.example.tutorplace.ui.theme.White

@Composable
fun RegistrationScreen(navController: NavController) {
	val viewModel = hiltViewModel<RegistrationViewModel>()
	val state by viewModel.state.collectAsState()
	val scrollState = rememberScrollState()
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
				title = stringResource(R.string.registration_title),
				description = null,
				onBackButtonClicked = { navController.popBackStack() }
			)
			Spacer(Modifier.weight(1f))
			PurpleButton(
				modifier = Modifier.fillMaxSize(),
				text = when (state.registrationStep) {
					is FirstStep -> stringResource(R.string.common_next)
					is SecondStep -> stringResource(R.string.registration_registration)
				},
				isLoading = state.isLoading,
				isEnabled = !state.isLoading,
			) { }
			AuthSectionDivider(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 8.dp)
			)
			YandexButton(modifier = Modifier.padding(top = 8.dp)) {
				// TODO Yandex button
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
						onClick = { },
					),
					SpanLinkData(
						link = stringResource(R.string.registration_terms_span),
						tag = "TERMS",
						style = SpanStyle(color = PurpleCC),
						onClick = {
							// TODO
						},
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
						onClick = {
							// TODO
						}
					)
				),
				textStyle = Typography.labelMedium.copy(textAlign = TextAlign.Center)
			)
		}
	}
}

@Preview
@Composable
private fun RegistrationScreenPreview() {
	RegistrationScreen(rememberNavController())
}