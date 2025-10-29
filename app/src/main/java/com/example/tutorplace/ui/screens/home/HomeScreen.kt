package com.example.tutorplace.ui.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tutorplace.R
import com.example.tutorplace.navigation.Destinations.FortuneWheelFlow
import com.example.tutorplace.ui.common.toolbar.ToolbarHeader
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheel.model.FortuneWheelParams
import com.example.tutorplace.ui.screens.home.presentation.HomeEffect
import com.example.tutorplace.ui.screens.home.presentation.HomeEvent
import com.example.tutorplace.ui.screens.home.presentation.HomeViewModel
import com.example.tutorplace.ui.screens.home.ui.FortuneWheelShortItem
import com.example.tutorplace.ui.theme.ScreenColor

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun HomeScreen(navController: NavHostController) {
	val viewModel = hiltViewModel<HomeViewModel>()
	val state = viewModel.state.collectAsState()
	ObserveViewModelEvents(viewModel, navController)
	Scaffold(
		topBar = {
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
		},
		containerColor = ScreenColor,
	) { paddingValues ->
		LazyColumn(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues)
		) {
			item {
				if (!state.value.fortuneWheelLastRotation.isLoading && state.value.fortuneWheelLastRotation.throwable == null) {
					FortuneWheelShortItem(
						modifier = Modifier.padding(top = 8.dp),
						lastRotationTime = state.value.fortuneWheelLastRotation.data,
						onInformationClick = { viewModel.onEvent(HomeEvent.UI.FortuneWheelInformationClicked) },
						onItemClick = { viewModel.onEvent(HomeEvent.UI.FortuneWheelClicked) }
					)
				} else {
					// TODO SKELETON
				}
			}
		}

	}
}

@Composable
private fun ObserveViewModelEvents(
	viewModel: HomeViewModel,
	navController: NavHostController
) {
	LaunchedEffect(Unit) {
		viewModel.effect.collect { effect ->
			when (effect) {
				HomeEffect.NavigateToMail -> navController
				HomeEffect.NavigateToProfile -> navController
				HomeEffect.NavigateToSearchScreen -> navController
				HomeEffect.NavigateToFortuneWheelInformationBottomSheet -> navController.navigate(
					FortuneWheelFlow.FortuneWheel(
						FortuneWheelParams(isShouldShowInformation = true)
					).route
				)
				HomeEffect.NavigateToFortuneWheelScreen -> navController.navigate(
					FortuneWheelFlow.FortuneWheel(
						FortuneWheelParams(isShouldShowInformation = false)
					).route
				)
			}
		}
	}
}

@RequiresApi(Build.VERSION_CODES.S)
@Preview
@Composable
private fun HomePreview() {
	HomeScreen(rememberNavController())
}