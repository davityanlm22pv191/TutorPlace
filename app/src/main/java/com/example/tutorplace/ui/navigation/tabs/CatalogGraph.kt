package com.example.tutorplace.ui.navigation.tabs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.tutorplace.ui.common.bottomnavbar.BottomTabBarItem
import com.example.tutorplace.ui.screens.catalog.CatalogScreen

fun NavGraphBuilder.catalogGraph(navController: NavHostController) {
	composable(BottomTabBarItem.Catalog.route) { CatalogScreen(navController) }
}