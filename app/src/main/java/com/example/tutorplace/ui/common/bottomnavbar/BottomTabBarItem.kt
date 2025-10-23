package com.example.tutorplace.ui.common.bottomnavbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.tutorplace.R
import com.example.tutorplace.navigation.Destinations

sealed class BottomTabBarItem(
	val route: String,
	@param:StringRes val label: Int,
	@param:DrawableRes val icon: Int,
) {
	object Catalog : BottomTabBarItem(
		Destinations.Catalog.route,
		R.string.tab_catalog_title,
		R.drawable.ic_catalog
	)

	object MyCourses : BottomTabBarItem(
		Destinations.MyCourses.route,
		R.string.tab_my_courses_title,
		R.drawable.ic_play
	)

	object Home : BottomTabBarItem(
		Destinations.Home.route,
		R.string.tab_home_title,
		R.drawable.ic_home
	)

	object Tasks : BottomTabBarItem(
		Destinations.Tasks.route,
		R.string.tab_tasks_title,
		R.drawable.ic_star
	)
}