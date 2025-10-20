package com.example.tutorplace.ui.screens.onboarding

import android.annotation.SuppressLint
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
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEffect.Hide
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.NextStepClicked
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.PreviousStepClicked
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingEvent.SkipButtonClicked
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState.Step.TELL_US_ABOUT_INTERESTS
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingViewModel
import com.example.tutorplace.ui.screens.onboarding.ui.uistate.OnboardingUiState
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
		confirmValueChange = { sheetValue -> sheetValue != SheetValue.Hidden }
	)
	val uiState = OnboardingUiState(state.value, viewModel)
	LaunchedEffect(Unit) { sheetState.show() }
	LaunchedEffect(Unit) {
		viewModel.effect.collect { effect ->
			when (effect) {
				is Hide -> navController.popBackStack()
			}
		}
	}
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
			logo = uiState.header,
			title = stringResource(uiState.title),
			description = uiState.description?.let { resId -> stringResource(resId) },
			onBackButtonClicked = {
				viewModel.onEvent(PreviousStepClicked)
			}.takeIf { uiState.isBackButtonVisible }
		)
		Spacer(modifier = Modifier.height(uiState.contentSeparatorHeightDp))

			@SuppressLint("UnusedContentLambdaTargetStateParameter")
			AnimatedContent(targetState = state.value.step) {
				Column { uiState.content.invoke(this@ModalBottomSheet) }
			}
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = if (state.value.step != TELL_US_ABOUT_INTERESTS) 20.dp else 0.dp)
				.shadow(8.dp, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
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
					text = stringResource(uiState.mainButtonTitle),
					isEnabled = state.value.isMainButtonEnabled,
					isLoading = state.value.onboardingInfo.isLoading,
					onClick = { viewModel.onEvent(NextStepClicked) }
				)
				AnimatedContent(
					targetState = uiState.isSkipButtonVisible
				) { isSkipButtonVisible ->
					if (isSkipButtonVisible) {
						TransparentButton(
							modifier = Modifier
								.fillMaxWidth()
								.height(45.dp),
							text = stringResource(R.string.common_skip),
							onClick = { viewModel.onEvent(SkipButtonClicked) }
						)
					}
				}
			}
		}
	}
}

@Preview
@Composable
fun OnboardingScreenPreview() {
	OnboardingScreen(rememberNavController())
}