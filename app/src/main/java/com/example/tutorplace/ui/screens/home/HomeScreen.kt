package com.example.tutorplace.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tutorplace.R
import com.example.tutorplace.ui.common.toolbar.ToolbarHeader
import com.example.tutorplace.ui.screens.home.presentation.HomeEffect
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent
import com.example.tutorplace.ui.screens.home.presentation.HomeViewModel
import com.example.tutorplace.ui.theme.ScreenColor

@Composable
fun HomeScreen(navController: NavHostController) {
	val viewModel = hiltViewModel<HomeViewModel>()
	val state = viewModel.state.collectAsState()
	ObserveViewModelEvents(viewModel, navController)
	Scaffold(
		containerColor = ScreenColor,
	) { paddingValues ->
		Column(
			modifier = Modifier.fillMaxSize()
		) {
			ToolbarHeader(
				modifier = Modifier,
				screenName = stringResource(R.string.home_screen_name),
				unreadEmailCount = state.value.profileShortInfo?.unreadMessageCount ?: 0,
				profileImageUrl = state.value.profileShortInfo?.profileThumbUrl.orEmpty(),
				level = state.value.profileShortInfo?.level?.level ?: 0,
				progress = state.value.profileShortInfo?.level?.let { (_, currentAmount, target) ->
					currentAmount / target.toFloat()
				} ?: 0f,
				isArrowVisible = false,
				isLoading = state.value.profileShortInfo == null,
				onBackClicked = {},
				onNotificationClicked = { viewModel.onEvent(HomeEvent.UI.NotificationClicked) },
				onSearchClicked = { viewModel.onEvent(HomeEvent.UI.SearchClicked) },
				onProfileClicked = { viewModel.onEvent(HomeEvent.UI.ProfileClicked) }
			)
		}
	}
}

@Composable
private fun ObserveViewModelEvents(
	viewModel: HomeViewModel,
	navController: NavController
) {
	LaunchedEffect(Unit) {
		viewModel.effect.collect { effect ->
			when (effect) {
				HomeEffect.NavigateToMail -> navController
				HomeEffect.NavigateToProfile -> navController
				HomeEffect.NavigateToSearchScreen -> navController
			}
		}
	}
}

@Preview
@Composable
private fun HomePreview() {
	HomeScreen(rememberNavController())
}