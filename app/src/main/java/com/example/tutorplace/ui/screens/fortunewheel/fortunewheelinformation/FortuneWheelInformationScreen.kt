package com.example.tutorplace.ui.screens.fortunewheel.fortunewheelinformation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheelinformation.presentation.FortuneWheelInformationViewModel
import com.example.tutorplace.ui.theme.ScreenColor
import com.example.tutorplace.ui.theme.Transparent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FortuneWheelInformationScreen(navController: NavController) {
	val viewModel = hiltViewModel<FortuneWheelInformationViewModel>()
	val state = viewModel.state.collectAsState()
	val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

	ModalBottomSheet(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight(),
		containerColor = ScreenColor,
		sheetState = sheetState,
		scrimColor = Transparent,
		onDismissRequest = { navController.popBackStack() }
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.verticalScroll(rememberScrollState())
				.navigationBarsPadding()
		) {

		}
	}
}

@Preview
@Composable
fun FortuneWheelInformationScreenPreview() {
	FortuneWheelInformationScreen(rememberNavController())
}
