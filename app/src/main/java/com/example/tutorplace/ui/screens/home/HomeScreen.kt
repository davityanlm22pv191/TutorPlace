package com.example.tutorplace.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tutorplace.ui.common.toolbar.ToolbarHeader
import com.example.tutorplace.ui.theme.ScreenColor

@Composable
fun HomeScreen(navController: NavHostController) {
	Scaffold(
		containerColor = ScreenColor
	) { paddingValues ->
		Column(
			modifier = Modifier.fillMaxSize()
		) {
			ToolbarHeader(
				screenName = "Дом",
				unreadEmailCount = 12,
				profileImageUrl = "",
				level = 1,
				progress = 0f,
				isArrowVisible = true,
				onBackClicked = {},
				onNotificationClicked = {},
				onSearchClicked = {},
				onProfileClicked = {}
			)
		}
	}
}

@Preview
@Composable
private fun HomePreview() {
	HomeScreen(rememberNavController())
}