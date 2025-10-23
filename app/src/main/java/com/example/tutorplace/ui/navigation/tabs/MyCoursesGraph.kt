package com.example.tutorplace.ui.navigation.tabs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.tutorplace.ui.common.bottomnavbar.BottomTabBarItem
import com.example.tutorplace.ui.screens.mycourses.MyCoursesScreen

fun NavGraphBuilder.myCoursesGraph(navController: NavHostController) {
	composable(BottomTabBarItem.MyCourses.route) { MyCoursesScreen(navController) }
}