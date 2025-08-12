package com.example.tutorplace.ui.activity.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.tutorplace.ui.navigation.AppNavigationGraph
import com.example.tutorplace.ui.theme.TutorPlaceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val startDestination = intent.getStringExtra("start_destination") ?: "auth"
		enableEdgeToEdge(
			statusBarStyle = SystemBarStyle.light(
				android.graphics.Color.WHITE,
				android.graphics.Color.WHITE
			),
			navigationBarStyle = SystemBarStyle.light(
				android.graphics.Color.WHITE,
				android.graphics.Color.WHITE
			)
		)
		setContent {
			TutorPlaceTheme {
				AppNavigationGraph(startDestination)
			}
		}
	}
}