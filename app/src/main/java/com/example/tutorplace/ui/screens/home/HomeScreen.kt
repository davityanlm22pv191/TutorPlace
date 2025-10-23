package com.example.tutorplace.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {
	Box(
		Modifier.fillMaxSize()
	) {
		Text(text = "This is Home Screen")
	}

}

@Preview
@Composable
private fun HomePreview() = HomeScreen()