package com.example.tutorplace.ui.screens.auth.authorization

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tutorplace.R
import com.example.tutorplace.ui.common.AuthSectionDivider
import com.example.tutorplace.ui.common.PurpleButton
import com.example.tutorplace.ui.navigation.Destinations
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationEvent
import com.example.tutorplace.ui.screens.auth.authorization.presentation.AuthorizationViewModel
import com.example.tutorplace.ui.theme.Black16
import com.example.tutorplace.ui.theme.GreyF8
import com.example.tutorplace.ui.theme.PurpleCC
import com.example.tutorplace.ui.theme.TutorPlaceTheme
import com.example.tutorplace.ui.theme.Typography

@Composable
fun AuthorizationScreen(navController: NavController) = TutorPlaceTheme {
	val viewModel = hiltViewModel<AuthorizationViewModel>()
	val state by viewModel.state.collectAsState()
	val scrollState = rememberScrollState()
	ObserveViewModelEvents(viewModel, navController)
	Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues)
				.padding(horizontal = 20.dp)
				.verticalScroll(scrollState)
				.imePadding(),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Image(
				modifier = Modifier.padding(top = 44.dp),
				painter = painterResource(R.drawable.ic_tutor_place_logo),
				contentDescription = null
			)
			Text(
				modifier = Modifier.padding(top = 24.dp),
				text = stringResource(R.string.authorization_enter_to_profile),
				style = Typography.headlineLarge,
				color = Black16
			)
			// TODO TEXT FIELD
			Text(
				modifier = Modifier
					.padding(top = 8.dp)
					.align(Alignment.Start),
				text = stringResource(R.string.authorization_restore_password),
				style = Typography.bodyMedium,
				color = PurpleCC,
			)
			Spacer(Modifier.weight(1f))
			PurpleButton(
				modifier = Modifier.fillMaxWidth(),
				text = stringResource(R.string.authorization_enter_to_profile),
				isEnabled = state.isLoginButtonEnabled
			) { }
			AuthSectionDivider(modifier = Modifier.padding(top = 8.dp))
			Button(
				modifier = Modifier
					.fillMaxWidth()
					.height(50.dp),
				shape = RoundedCornerShape(12.dp),
				colors = ButtonDefaults.buttonColors().copy(containerColor = GreyF8),
				onClick = { }
			) {
				Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
					Image(
						modifier = Modifier.align(Alignment.CenterStart),
						painter = painterResource(R.drawable.ic_yandex_logo),
						contentDescription = null
					)
					Text(
						text = stringResource(R.string.authorization_entry_by_yandex_id),
						style = Typography.bodyLarge,
						color = Black16
					)
				}
			}
		}
	}
}

@Composable
private fun ObserveViewModelEvents(
	viewModel: AuthorizationViewModel,
	navController: NavController
) {
	LaunchedEffect(Unit) {
		viewModel.event.collect { event ->
			when (event) {
				AuthorizationEvent.OnHome -> navController.navigate(Destinations.Home.route)
			}
		}
	}
}

@Preview
@Composable
private fun AuthorizationScreenPreview() = AuthorizationScreen(rememberNavController())