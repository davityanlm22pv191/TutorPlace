package com.example.tutorplace.ui.screens.catalog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun CatalogScreen(navController: NavController) {
	Box(
		Modifier.fillMaxSize()
	) {
		Text(text = "This is Catalog Screen")
	}
}

@Preview
@Composable
private fun TasksPreview() = CatalogScreen(rememberNavController())