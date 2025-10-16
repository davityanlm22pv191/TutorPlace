package com.example.tutorplace.ui.screens.onboarding.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tutorplace.ui.common.SkeletonShimmer
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState
import com.example.tutorplace.ui.theme.GreyD5

@Composable
fun OnboardingMain(
	state: OnboardingState,
	columnScope: ColumnScope
) = with(columnScope) {
	var isLoading by remember { mutableStateOf(true) }
	Box {
		if (isLoading) {
			SkeletonShimmer {
				Box(
					Modifier
						.fillMaxWidth()
						.height(200.dp)
						.padding(horizontal = 16.dp)
						.background(color = GreyD5, RoundedCornerShape(20.dp))
				)
			}
		}
		AsyncImage(
			modifier = Modifier
				.fillMaxWidth()
				.height(200.dp)
				.padding(horizontal = 16.dp),
			model = state.onboardingInfo.data.coverUrl,
			contentDescription = null,
			contentScale = ContentScale.Crop,
			onSuccess = { isLoading = false },
			onError = { isLoading = false }
		)
	}
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun OnboardingMainPreview() {
	Column {
		OnboardingMain(
			state = OnboardingState(
				step = OnboardingState.Step.WELCOME
			),
			columnScope = this
		)
	}
}