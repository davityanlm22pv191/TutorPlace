package com.example.tutorplace.ui.activity.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.tutorplace.ui.navigation.AppNavigationGraph
import com.example.tutorplace.ui.theme.TutorPlaceTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val startDestination = intent.getStringExtra("start_destination") ?: "auth"
		enableEdgeToEdge()
		setContent {
			TutorPlaceTheme {
				AppNavigationGraph(startDestination)
			}
		}
	}
}