package com.example.tutorplace.ui.base.main

import androidx.compose.foundation.layout.WindowInsets
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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tutorplace.navigation.Destinations
import com.example.tutorplace.navigation.Destinations.MainScreen.MainScreenParams
import com.example.tutorplace.navigation.tabs.TabsNavHost
import com.example.tutorplace.ui.base.main.presentation.MainScreenViewModel
import com.example.tutorplace.ui.common.bottomnavbar.BottomNavigationBar
import com.example.tutorplace.ui.common.bottomnavbar.BottomTabBarItem

@Composable
fun MainScreen(navController: NavHostController, params: MainScreenParams) {
	val viewModel = hiltViewModel<MainScreenViewModel>()
	OpenOnboardingIfNeeded(navController, params.isShouldShowOnboarding)
	val bottomNavController = rememberNavController()
	val bottomNavigationBarItems = listOf(
		BottomTabBarItem.Catalog,
		BottomTabBarItem.MyCourses,
		BottomTabBarItem.Home,
		BottomTabBarItem.Tasks
	)
	Scaffold(
		contentWindowInsets = WindowInsets(0, 0, 0, 0),
		bottomBar = { BottomNavigationBar(bottomNavController, bottomNavigationBarItems) }
	) { paddingValues ->
		TabsNavHost(
			modifier = Modifier.padding(paddingValues),
			navController = bottomNavController,
			startDestination = Destinations.Home.route
		)
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