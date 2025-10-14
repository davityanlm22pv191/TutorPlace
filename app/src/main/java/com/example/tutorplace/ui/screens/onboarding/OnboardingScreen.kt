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
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.HelpYouStay
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.KnowledgeFromMasters
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.Main
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.MoreOpportunities
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.ProvideDetails
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.Quizzes
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.SpendYourTimeProductively
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.TellUsAboutInterests
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingViewModel
import com.example.tutorplace.ui.screens.onboarding.ui.OnboardingHelpYouStay
import com.example.tutorplace.ui.screens.onboarding.ui.OnboardingKnowledgeFromMasters
import com.example.tutorplace.ui.screens.onboarding.ui.OnboardingMain
import com.example.tutorplace.ui.screens.onboarding.ui.OnboardingMoreOpportunities
import com.example.tutorplace.ui.screens.onboarding.ui.OnboardingProvideDetails
import com.example.tutorplace.ui.screens.onboarding.ui.OnboardingQuizzes
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
			logo = state.value.headerLogoType(),
			title = stringResource(state.value.title()),
			description = state.value.description()?.let { resId -> stringResource(resId) },
			onBackButtonClicked = {
				viewModel.onEvent(OnboardingEvent.PreviousStepClicked)
			}.takeIf { state.value.isBackButtonVisible }
		)
		Spacer(modifier = Modifier.height(state.value.contentSeparatorHeight()))
		AnimatedContent(targetState = state.value) { stepState -> stepState.Content(viewModel) }
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
					isLoading = state.value.isLoading,
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

private fun OnboardingState.contentSeparatorHeight() = when (this) {
	is Quizzes -> 12.dp
	is Main, is SpendYourTimeProductively -> 40.dp
	is ProvideDetails, is KnowledgeFromMasters -> 16.dp
	is MoreOpportunities -> 36.dp
	is TellUsAboutInterests, is HelpYouStay -> 24.dp
}

private fun OnboardingState.headerLogoType() = when (this) {
	is Quizzes, is Main -> Image(R.drawable.ic_tutor_place_logo, paddingTop = 0)
	is ProvideDetails -> Text(R.string.onboarding_provide_details_logo)
	is MoreOpportunities -> Text(R.string.onboarding_more_opportunities_logo)
	is KnowledgeFromMasters -> Text(R.string.onboarding_knowledge_from_masters_logo)
	is TellUsAboutInterests -> Text(R.string.onboarding_tell_us_about_interests_logo)
	is HelpYouStay -> Text(R.string.onboarding_help_you_stay_logo)
	is SpendYourTimeProductively -> Text(R.string.onboarding_spend_your_time_productively_logo)
}

@StringRes
private fun OnboardingState.title() = when (this) {
	is Quizzes, is Main -> R.string.onboarding_quizzes_welcome_to_tutor_place
	is ProvideDetails -> R.string.onboarding_provide_details_title
	is MoreOpportunities -> R.string.onboarding_more_opportunities_title
	is KnowledgeFromMasters -> R.string.onboarding_knowledge_from_masters_title
	is TellUsAboutInterests -> R.string.onboarding_tell_us_about_interests_title
	is HelpYouStay -> R.string.onboarding_help_you_stay_title
	is SpendYourTimeProductively -> R.string.onboarding_spend_your_time_productively_title
}

@StringRes
private fun OnboardingState.description() = when (this) {
	is Quizzes -> R.string.onboarding_quizzes_description
	is Main -> R.string.onboarding_main_description
	is ProvideDetails -> null
	is MoreOpportunities -> R.string.onboarding_more_opportunities_title
	is KnowledgeFromMasters -> R.string.onboarding_knowledge_from_masters_description
	is TellUsAboutInterests -> R.string.onboarding_tell_us_about_interests_description
	is HelpYouStay -> R.string.onboarding_help_you_stay_description
	is SpendYourTimeProductively -> R.string.onboarding_spend_your_time_productively_description
}

@Composable
private fun OnboardingState.Content(viewModel: OnboardingViewModel) {
	Column {
		when (val stepState = this@Content) {
			is Quizzes -> OnboardingQuizzes(
				stepState,
				columnScope = this
			)
			is Main -> OnboardingMain(
				stepState,
				columnScope = this
			)
			is ProvideDetails -> OnboardingProvideDetails(
				stepState,
				this
			)
			is MoreOpportunities -> OnboardingMoreOpportunities(
				stepState,
				columnScope = this
			)
			is KnowledgeFromMasters -> OnboardingKnowledgeFromMasters(
				stepState,
				columnScope = this
			)
			is TellUsAboutInterests -> OnboardingTellUsAboutInterests(
				stepState,
				columnScope = this
			)
			is HelpYouStay -> OnboardingHelpYouStay(
				stepState,
				columnScope = this
			)
			is SpendYourTimeProductively -> OnboardingSpendYourTimeProductively(
				stepState,
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