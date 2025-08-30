package com.example.tutorplace.ui.screens.home

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
import com.example.tutorplace.ui.navigation.Destinations.Home.HomeParams
import com.example.tutorplace.ui.theme.TutorPlaceTheme

@Composable
fun HomeScreen(navController: NavController, params: HomeParams) {
	TutorPlaceTheme {
		Scaffold { paddingValues ->
			Box(
				Modifier
					.fillMaxSize()
					.padding(paddingValues)
			) {
				Text(
					modifier = Modifier.align(Alignment.Center),
					text = "isShouldShowOnboarding = ${params.isShouldShowOnboarding}"
				)
			}
		}
	}
}

@Preview
@Composable
private fun HomePreview() = HomeScreen(rememberNavController(), HomeParams())