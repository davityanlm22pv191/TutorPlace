package com.example.tutorplace.ui.screens.auth.restorepassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tutorplace.ui.theme.TutorPlaceTheme
import com.example.tutorplace.ui.theme.White

@Composable
fun RestorePasswordScreen(navController: NavController) {
	TutorPlaceTheme {
		Scaffold(containerColor = White) { paddingValues ->
			Column(
				modifier = Modifier.padding(paddingValues),
				horizontalAlignment = Alignment.CenterHorizontally
			) {

			}
		}
	}
}

@Preview
@Composable
private fun RestorePasswordScreenPreview() {
	RestorePasswordScreen(rememberNavController())
}