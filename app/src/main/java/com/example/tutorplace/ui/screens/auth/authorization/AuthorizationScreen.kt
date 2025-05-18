package com.example.tutorplace.ui.screens.auth.authorization

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tutorplace.ui.theme.TutorPlaceTheme

@Composable
fun AuthorizationScreen(navController: NavController) {
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
	AuthorizationScreen(rememberNavController())
}