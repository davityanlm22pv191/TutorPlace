package com.example.tutorplace.ui.common.bottomnavbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.tutorplace.R
import com.example.tutorplace.navigation.Destinations
import kotlinx.serialization.Serializable

@Serializable
enum class BottomTabBarItem(
	@param:StringRes val label: Int,
	@param:DrawableRes val icon: Int,
	val route: String,
) {
	Catalog(
		route = Destinations.Tabs.Catalog.route,
		label = R.string.tab_catalog_title,
		icon = R.drawable.ic_catalog,
	),
	MyCourses(
		route = Destinations.Tabs.MyCourses.route,
		label = R.string.tab_my_courses_title,
		icon = R.drawable.ic_play
	),
	Home(
		route = Destinations.Tabs.Home.route,
		label = R.string.tab_home_title,
		icon = R.drawable.ic_home
	),
	Tasks(
		route = Destinations.Tabs.Tasks.route,
		label = R.string.tab_tasks_title,
		icon = R.drawable.ic_star
	)
}