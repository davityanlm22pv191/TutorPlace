package com.example.tutorplace.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TutorPlaceScaffold(
	modifier: Modifier = Modifier,
	content: @Composable (PaddingValues) -> Unit
) {

}

@Preview
@Composable
fun TutorPlaceScaffoldPreview() {
	TutorPlaceScaffold(content = {})
}