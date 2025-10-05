package com.example.tutorplace.ui.screens.onboarding

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
import com.example.tutorplace.ui.theme.ScreenColor
import com.example.tutorplace.ui.theme.Transparent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(navController: NavController) {
	val viewModel = hiltViewModel<OnboardingViewModel>()
	val state = viewModel.state.collectAsState()
	val sheetState = rememberModalBottomSheetState()
	LaunchedEffect(Unit) { sheetState.show() }

	ModalBottomSheet(
		modifier = Modifier
			.fillMaxWidth()
			.heightIn(min = 620.dp),
		containerColor = ScreenColor,
		sheetState = sheetState,
		scrimColor = Transparent,
		onDismissRequest = { navController.popBackStack() }
	) {
		AnimatedContent(state) { onboardingState ->
			when (val stepState = onboardingState.value) {
				is Quizzes -> OnboardingQuizzes(
					stepState,
					columnScope = this@ModalBottomSheet
				)
				is Main -> OnboardingMain(
					stepState,
					columnScope = this@ModalBottomSheet
				)
				is ProvideDetails -> OnboardingProvideDetails(
					stepState,
					this@ModalBottomSheet
				)
				is MoreOpportunities -> OnboardingMoreOpportunities(
					stepState,
					columnScope = this@ModalBottomSheet
				)
				is KnowledgeFromMasters -> OnboardingKnowledgeFromMasters(
					stepState,
					columnScope = this@ModalBottomSheet
				)
				is TellUsAboutInterests -> OnboardingTellUsAboutInterests(
					stepState,
					columnScope = this@ModalBottomSheet
				)
				is HelpYouStay -> OnboardingHelpYouStay(
					stepState,
					columnScope = this@ModalBottomSheet
				)
				is SpendYourTimeProductively -> OnboardingSpendYourTimeProductively(
					stepState,
					columnScope = this@ModalBottomSheet
				)

			}
		}
	}
}

@Preview
@Composable
fun OnboardingScreenPreview() {
	OnboardingScreen(rememberNavController())
}