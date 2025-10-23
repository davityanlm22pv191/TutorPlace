package com.example.tutorplace.ui.screens.mycourses

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun MyCoursesScreen(navController: NavHostController) {
	Box(
		Modifier.fillMaxSize()
	) {
		Text(text = "This is My Courses Screen")
	}
}

@Preview
@Composable
private fun MyCoursesPreview() {
	MyCoursesScreen(rememberNavController())
}