package com.example.tutorplace.navigation.tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun TabsNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String,
    rootNavController: NavHostController,
) {
	NavHost(
		modifier = modifier,
		navController = navController,
		startDestination = startDestination,
	) {
        catalogGraph(navController)
        myCoursesGraph(navController)
        homeGraph(navController, rootNavController)
        tasksGraph(navController)
	}
}