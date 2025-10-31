package com.example.tutorplace.navigation.tabs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.tutorplace.navigation.Destinations
import com.example.tutorplace.ui.screens.catalog.CatalogScreen

fun NavGraphBuilder.catalogGraph(navController: NavHostController) {
	composable(Destinations.Tabs.Catalog.route) { CatalogScreen(navController) }
}