package com.example.tutorplace.ui.fragment.auth.authorization.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tutorplace.ui.fragment.auth.authorization.presentation.AuthorizationCommand
import com.example.tutorplace.ui.fragment.auth.authorization.presentation.AuthorizationState
import com.example.tutorplace.ui.theme.TutorPlaceTheme

@Composable
fun AuthorizationScreen(state: AuthorizationState, onCommand: (AuthorizationCommand) -> Unit) {
	TutorPlaceTheme {
		Scaffold {
			Box(
				modifier = Modifier
					.fillMaxSize()
					.padding(it),
				contentAlignment = Alignment.Center
			) {
				Text(text = "This is Authorization screen")
			}
		}
	}
}

@Preview
@Composable
private fun AuthorizationScreenPreview() {
	AuthorizationScreen(AuthorizationState()) { }
}