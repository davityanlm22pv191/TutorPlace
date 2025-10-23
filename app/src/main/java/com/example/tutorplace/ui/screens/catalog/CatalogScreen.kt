package com.example.tutorplace.ui.screens.catalog

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun CatalogScreen(navController: NavHostController) {
	Text(text = "This is Catalog Screen")
}

@Preview
@Composable
private fun CatalogPreview() {
	CatalogScreen(rememberNavController())
}