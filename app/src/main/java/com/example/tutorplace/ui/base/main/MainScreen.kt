package com.example.tutorplace.ui.base.main

import android.Manifest
import android.os.Build
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tutorplace.navigation.Destinations
import com.example.tutorplace.navigation.Destinations.MainScreen.MainScreenParams
import com.example.tutorplace.navigation.tabs.TabsNavHost
import com.example.tutorplace.ui.base.main.presentation.MainScreenViewModel
import com.example.tutorplace.ui.common.RequestPermission
import com.example.tutorplace.ui.common.bottomnavbar.BottomNavigationBar

@Composable
fun MainScreen(navController: NavHostController, params: MainScreenParams) {
	val viewModel = hiltViewModel<MainScreenViewModel>()
	OpenOnboardingIfNeeded(navController, params.isShouldShowOnboarding)
	MainScreen(rootNavController = navController)
}

@Composable
private fun MainScreen(rootNavController: NavHostController) {
	val bottomNavController = rememberNavController()
	Scaffold(
		contentWindowInsets = WindowInsets(0, 0, 0, 0),
		bottomBar = {
			BottomNavigationBar(bottomNavController)
//			AnimatedContent(
//				shouldShowBottomBar,
//				label = "shouldShowBottomBar",
//			) {
//				if (it) {
//					BottomNavigationBar(bottomNavController, bottomNavigationBarItems)
//				}
//			}
		}
	) { paddingValues ->
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			RequestPermission(Manifest.permission.POST_NOTIFICATIONS) {}
		}
		TabsNavHost(
			modifier = Modifier.padding(paddingValues),
			navController = bottomNavController,
			startDestination = Destinations.Tabs.Home.route,
		)
	}
}

@Composable
private fun OpenOnboardingIfNeeded(
	navController: NavHostController,
	isShouldShowOnboarding: Boolean
) {
	var onboardingNavigated by rememberSaveable { mutableStateOf(false) }
	LaunchedEffect(isShouldShowOnboarding) {
		if (isShouldShowOnboarding && !onboardingNavigated) {
			onboardingNavigated = true
			navController.navigate(Destinations.Onboarding.route)
		}
	}
}

@Preview
@Composable
private fun MainScreenPreview() {
	MainScreen(rootNavController = rememberNavController())
}