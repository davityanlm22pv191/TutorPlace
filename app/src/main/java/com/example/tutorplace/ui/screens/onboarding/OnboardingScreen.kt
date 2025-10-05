package com.example.tutorplace.ui.screens.onboarding

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
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingViewModel
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
		// TODO Скачать иконку R.drawable.ic_tutor_place_logo в норм качестве
		// Написать функцию, которая будет переключаться в зависимости от шага
	}
}

@Preview
@Composable
fun OnboardingScreenPreview() {
	OnboardingScreen(rememberNavController())
}