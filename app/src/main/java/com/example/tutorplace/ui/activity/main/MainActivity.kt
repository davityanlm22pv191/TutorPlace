package com.example.tutorplace.ui.activity.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.SideEffect
import androidx.core.view.WindowInsetsControllerCompat
import com.example.tutorplace.helpers.isLight
import com.example.tutorplace.navigation.AppNavigationGraph
import com.example.tutorplace.ui.theme.ScreenColor
import com.example.tutorplace.ui.theme.TutorPlaceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val startDestination = intent.getStringExtra("start_destination") ?: "auth"
		enableEdgeToEdge()
		setContent {
			TutorPlaceTheme {
				val isLightScreenColor = ScreenColor.isLight()
				SideEffect {
					val windowInsetsController =
						WindowInsetsControllerCompat(window, window.decorView)
					windowInsetsController.isAppearanceLightStatusBars = isLightScreenColor
					windowInsetsController.isAppearanceLightNavigationBars = isLightScreenColor
				}

				AppNavigationGraph(startDestination)
			}
		}
	}
}


