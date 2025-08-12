package com.example.tutorplace.ui.screens.auth.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection.Ltr
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tutorplace.R
import com.example.tutorplace.ui.screens.auth.common.Header
import com.example.tutorplace.ui.theme.White

@Composable
fun RegistrationScreen(navController: NavController) {
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
		}
	}
}

@Preview
@Composable
private fun RegistrationScreenPreview() {
	RegistrationScreen(rememberNavController())
}