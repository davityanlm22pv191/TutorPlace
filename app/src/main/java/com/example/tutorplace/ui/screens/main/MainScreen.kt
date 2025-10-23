package com.example.tutorplace.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tutorplace.ui.common.BottomNavigationBar
import com.example.tutorplace.ui.navigation.BottomTabBarItem
import com.example.tutorplace.ui.navigation.Destinations
import com.example.tutorplace.ui.navigation.Destinations.MainScreen.MainScreenParams
import com.example.tutorplace.ui.screens.catalog.CatalogScreen
import com.example.tutorplace.ui.screens.home.HomeScreen
import com.example.tutorplace.ui.screens.mycourses.MyCoursesScreen
import com.example.tutorplace.ui.screens.tasks.TasksScreen

@Composable
fun MainScreen(navController: NavHostController, params: MainScreenParams) {
	OpenOnboardingIfNeeded(navController, params.isShouldShowOnboarding)
	val bottomNavController = rememberNavController()
	val bottomNavigationBarItems = listOf(
		BottomTabBarItem.Catalog,
		BottomTabBarItem.MyCourses,
		BottomTabBarItem.Home,
		BottomTabBarItem.Tasks
	)
	Scaffold(
		bottomBar = { BottomNavigationBar(bottomNavController, bottomNavigationBarItems) }
	) { paddingValues ->
		NavHost(
			modifier = Modifier.padding(paddingValues),
			navController = bottomNavController,
			startDestination = BottomTabBarItem.Home.route,
		) {
			composable(BottomTabBarItem.Catalog.route) { CatalogScreen() }
			composable(BottomTabBarItem.MyCourses.route) { MyCoursesScreen() }
			composable(BottomTabBarItem.Home.route) { HomeScreen() }
			composable(BottomTabBarItem.Tasks.route) { TasksScreen() }
		}
	}
}

@Composable
private fun OpenOnboardingIfNeeded(
	navController: NavController,
	isShouldShowOnboarding: Boolean
) {
	var alreadyNavigated by rememberSaveable { mutableStateOf(false) }
	LaunchedEffect(isShouldShowOnboarding) {
		if (isShouldShowOnboarding && !alreadyNavigated) {
			alreadyNavigated = true
			navController.navigate(Destinations.Onboarding.route)
		}
	}
}

@Preview
@Composable
private fun MainScreenPreview() {
	MainScreen(rememberNavController(), MainScreenParams(false))
}