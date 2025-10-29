package com.example.tutorplace.ui.screens.fortunewheel.fortunewheel

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tutorplace.navigation.Destinations
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheel.model.FortuneWheelParams
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheel.presentation.FortuneWheelViewModel

@Composable
fun FortuneWheelScreen(navController: NavHostController, params: FortuneWheelParams) {
	val viewModel = hiltViewModel<FortuneWheelViewModel>()
	val state = viewModel.state.collectAsState()
	OpenInformationBottomSheetIfNeeded(navController, params.isShouldShowInformation)
	Scaffold(
		modifier = Modifier.fillMaxSize()
	) { paddingValues ->
		Text(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues),
			text = "Fortune Wheel Screen",
			textAlign = TextAlign.Center
		)
	}
}

@Composable
private fun OpenInformationBottomSheetIfNeeded(
	navController: NavHostController,
	isShouldShowInformation: Boolean
) {
	var informationNavigated by rememberSaveable { mutableStateOf(false) }
	LaunchedEffect(isShouldShowInformation) {
		if (isShouldShowInformation && !informationNavigated) {
			informationNavigated = true
			navController.navigate(Destinations.FortuneWheelFlow.FortuneWheelInformation.route)
		}
	}
}

@Preview
@Composable
fun FortuneWheelScreenPreview() {
	FortuneWheelScreen(rememberNavController(), params = FortuneWheelParams(false))
}
