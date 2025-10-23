package com.example.tutorplace.ui.navigation.tabs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.tutorplace.ui.common.bottomnavbar.BottomTabBarItem
import com.example.tutorplace.ui.screens.tasks.TasksScreen

fun NavGraphBuilder.tasksGraph(navController: NavHostController) {
	composable(BottomTabBarItem.Tasks.route) { TasksScreen(navController) }
}