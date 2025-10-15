package com.example.tutorplace.ui.screens.onboarding

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tutorplace.R
import com.example.tutorplace.ui.common.PurpleButton
import com.example.tutorplace.ui.common.TransparentButton
import com.example.tutorplace.ui.common.header.Header
import com.example.tutorplace.ui.common.header.HeaderLogoType.Image
import com.example.tutorplace.ui.common.header.HeaderLogoType.Text
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.Step.CONGRATULATIONS
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.Step.HELP_YOU_STAY
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.Step.KNOWLEDGE_FROM_MASTERS
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.Step.MORE_OPPORTUNITIES
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.Step.PROVIDE_DETAILS
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.Step.SPEND_YOUR_TIME_PRODUCTIVELY
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.Step.TELL_US_ABOUT_INTERESTS
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.Step.WELCOME
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingViewModel
import com.example.tutorplace.ui.screens.onboarding.ui.OnboardingCongratulations
import com.example.tutorplace.ui.screens.onboarding.ui.OnboardingHelpYouStay
import com.example.tutorplace.ui.screens.onboarding.ui.OnboardingKnowledgeFromMasters
import com.example.tutorplace.ui.screens.onboarding.ui.OnboardingMain
import com.example.tutorplace.ui.screens.onboarding.ui.OnboardingMoreOpportunities
import com.example.tutorplace.ui.screens.onboarding.ui.OnboardingProvideDetails
import com.example.tutorplace.ui.screens.onboarding.ui.OnboardingSpendYourTimeProductively
import com.example.tutorplace.ui.screens.onboarding.ui.OnboardingTellUsAboutInterests
import com.example.tutorplace.ui.theme.ContainerColor
import com.example.tutorplace.ui.theme.ScreenColor
import com.example.tutorplace.ui.theme.Transparent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(navController: NavController) {
	val viewModel = hiltViewModel<OnboardingViewModel>()
	val state = viewModel.state.collectAsState()
	val sheetState = rememberModalBottomSheetState(
		skipPartiallyExpanded = true,
	)
	LaunchedEffect(Unit) { sheetState.show() }

	ModalBottomSheet(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight(),
		containerColor = ScreenColor,
		sheetState = sheetState,
		scrimColor = Transparent,
		onDismissRequest = { navController.popBackStack() }
	) {
		Header(
			logo = state.value.step.headerLogoType(),
			title = stringResource(state.value.step.title()),
			description = state.value.step.description()?.let { resId -> stringResource(resId) },
			onBackButtonClicked = {
				viewModel.onEvent(OnboardingEvent.PreviousStepClicked)
			}.takeIf { state.value.isBackButtonVisible }
		)
		Spacer(modifier = Modifier.height(state.value.step.contentSeparatorHeight()))
		AnimatedContent(targetState = state.value.step) { state.value.Content(it, viewModel) }
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 20.dp)
				.background(ContainerColor, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
				.padding(16.dp),
		) {
			Column(
				modifier = Modifier.fillMaxWidth(),
				verticalArrangement = Arrangement.spacedBy(8.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				PurpleButton(
					modifier = Modifier
						.fillMaxWidth()
						.height(45.dp),
					text = stringResource(state.value.mainButtonTitle),
					isEnabled = state.value.isMainButtonEnabled,
					isLoading = state.value.onboardingInfo.isLoading,
					onClick = { viewModel.onEvent(OnboardingEvent.NextStepClicked) }
				)
				AnimatedContent(
					targetState = state.value.isSkipButtonVisible
				) { isSkipButtonVisible ->
					if (isSkipButtonVisible) {
						TransparentButton(
							modifier = Modifier
								.fillMaxWidth()
								.height(45.dp),
							text = stringResource(R.string.common_skip),
							onClick = { viewModel.onEvent(OnboardingEvent.PreviousStepClicked) }
						)
					}
				}
			}
		}
	}
}

private fun OnboardingState.Step.contentSeparatorHeight() = when (this) {
	CONGRATULATIONS -> 12.dp
	WELCOME, SPEND_YOUR_TIME_PRODUCTIVELY -> 40.dp
	PROVIDE_DETAILS, KNOWLEDGE_FROM_MASTERS -> 16.dp
	MORE_OPPORTUNITIES -> 36.dp
	TELL_US_ABOUT_INTERESTS, HELP_YOU_STAY -> 24.dp
}

private fun OnboardingState.Step.headerLogoType() = when (this) {
	CONGRATULATIONS, WELCOME -> Image(R.drawable.ic_tutor_place_logo, paddingTop = 0)
	PROVIDE_DETAILS -> Text(R.string.onboarding_provide_details_logo)
	MORE_OPPORTUNITIES -> Text(R.string.onboarding_more_opportunities_logo)
	KNOWLEDGE_FROM_MASTERS -> Text(R.string.onboarding_knowledge_from_masters_logo)
	TELL_US_ABOUT_INTERESTS -> Text(R.string.onboarding_tell_us_about_interests_logo)
	HELP_YOU_STAY -> Text(R.string.onboarding_help_you_stay_logo)
	SPEND_YOUR_TIME_PRODUCTIVELY -> Text(R.string.onboarding_spend_your_time_productively_logo)
}

@StringRes
private fun OnboardingState.Step.title() = when (this) {
	CONGRATULATIONS, WELCOME -> R.string.onboarding_congratulations_welcome_to_tutor_place
	PROVIDE_DETAILS -> R.string.onboarding_provide_details_title
	MORE_OPPORTUNITIES -> R.string.onboarding_more_opportunities_title
	KNOWLEDGE_FROM_MASTERS -> R.string.onboarding_knowledge_from_masters_title
	TELL_US_ABOUT_INTERESTS -> R.string.onboarding_tell_us_about_interests_title
	HELP_YOU_STAY -> R.string.onboarding_help_you_stay_title
	SPEND_YOUR_TIME_PRODUCTIVELY -> R.string.onboarding_spend_your_time_productively_title
}

@StringRes
private fun OnboardingState.Step.description() = when (this) {
	CONGRATULATIONS -> R.string.onboarding_congratulations_description
	WELCOME -> R.string.onboarding_welcome_description
	PROVIDE_DETAILS -> null
	MORE_OPPORTUNITIES -> R.string.onboarding_more_opportunities_title
	KNOWLEDGE_FROM_MASTERS -> R.string.onboarding_knowledge_from_masters_description
	TELL_US_ABOUT_INTERESTS -> R.string.onboarding_tell_us_about_interests_description
	HELP_YOU_STAY -> R.string.onboarding_help_you_stay_description
	SPEND_YOUR_TIME_PRODUCTIVELY -> R.string.onboarding_spend_your_time_productively_description
}

@Composable
private fun OnboardingState.Content(step: OnboardingState.Step, viewModel: OnboardingViewModel) {
	Column {
		when (step) {
			CONGRATULATIONS -> OnboardingCongratulations(
				this@Content,
				columnScope = this
			)
			WELCOME -> OnboardingMain(
				this@Content,
				columnScope = this
			)
			PROVIDE_DETAILS -> OnboardingProvideDetails(
				this@Content,
				columnScope = this,
				onUserNameChanged = { userName ->
					viewModel.onEvent(OnboardingEvent.NameValueChanged(userName))
				},
				onPasswordChanged = { password ->
					viewModel.onEvent(OnboardingEvent.PasswordValueChanged(password))
				},
				onRepeatedPasswordChanged = { password ->
					viewModel.onEvent(OnboardingEvent.RepeatPasswordValueChanged(password))
				},
				onSexChosen = { sex -> viewModel.onEvent(OnboardingEvent.SexChosen(sex)) },
			)
			MORE_OPPORTUNITIES -> OnboardingMoreOpportunities(
				this@Content,
				columnScope = this
			)
			KNOWLEDGE_FROM_MASTERS -> OnboardingKnowledgeFromMasters(
				this@Content,
				columnScope = this
			)
			TELL_US_ABOUT_INTERESTS -> OnboardingTellUsAboutInterests(
				this@Content,
				columnScope = this
			)
			HELP_YOU_STAY -> OnboardingHelpYouStay(
				this@Content,
				columnScope = this
			)
			SPEND_YOUR_TIME_PRODUCTIVELY -> OnboardingSpendYourTimeProductively(
				this@Content,
				columnScope = this
			)
		}
	}
}

@Preview
@Composable
fun OnboardingScreenPreview() {
	OnboardingScreen(rememberNavController())
}