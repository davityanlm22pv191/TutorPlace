package com.example.tutorplace.ui.common.bottomnavbar

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tutorplace.ui.theme.ContainerColor
import com.example.tutorplace.ui.theme.Grey82
import com.example.tutorplace.ui.theme.PurpleCC
import com.example.tutorplace.ui.theme.Transparent
import com.example.tutorplace.ui.theme.Typography
import kotlin.reflect.KClass

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
	this?.hierarchy?.any { it.hasRoute(route) } ?: false

@Composable
fun BottomNavigationBar(bottomNavController: NavHostController) {
	NavigationBar(
		modifier = Modifier
			.shadow(4.dp, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
			.background(ContainerColor, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
			.clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
		containerColor = ContainerColor,
	) {
		val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
		val currentDestination = navBackStackEntry?.destination
		BottomTabBarItem.entries.forEach { tabBarItem ->
			val isSelected = false//currentDestination.isRouteInHierarchy(tabBarItem)
			NavigationBarItem(
				selected = isSelected,
				label = {
					Text(text = stringResource(tabBarItem.label), style = Typography.bodySmall)
				},
				icon = {
					Icon(painter = painterResource(tabBarItem.icon), contentDescription = null)
				},
				onClick = {
					if (!isSelected) {
						bottomNavController.navigate(tabBarItem.route) {
							popUpTo(bottomNavController.graph.findStartDestination().id) {
								saveState = true
							}
							launchSingleTop = true
							restoreState = true
						}
					}
				},
				colors = NavigationBarItemDefaults.colors(
					selectedIconColor = PurpleCC,
					unselectedIconColor = Grey82,
					selectedTextColor = PurpleCC,
					unselectedTextColor = Grey82,
					indicatorColor = Transparent
				)
			)
		}
	}
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun BottomNavigationBarPreview() {
	BottomNavigationBar(rememberNavController())
}