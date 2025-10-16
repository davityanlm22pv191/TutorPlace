package com.example.tutorplace.ui.screens.onboarding.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
fun OnboardingKnowledgeFromMasters(
	state: OnboardingState,
	columnScope: ColumnScope
) = with(columnScope) {
	val pagerState = rememberPagerState(
		initialPage = (state.onboardingInfo.data.coursesCoverUrls.size / 2f).roundToInt() - 1,
		pageCount = { state.onboardingInfo.data.coursesCoverUrls.size }
	)
	HorizontalPager(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight(),
		state = pagerState,
		contentPadding = PaddingValues(horizontal = 48.dp),
	) { pageIndex ->
		val pageOffset = ((pagerState.currentPage - pageIndex) + pagerState.currentPageOffsetFraction).absoluteValue
		val scale = 0.85f + (1f - pageOffset.coerceIn(0f, 1f)) * 0.35f
		val isSelected = pageIndex == pagerState.currentPage
		Box(
			modifier = Modifier
				.graphicsLayer {
					scaleX = if (isSelected) scale else 1f
					scaleY = if (isSelected) scale else 1f
				}
				.width(272.dp)
				.height(160.dp)
				.clip(RoundedCornerShape(12.dp)),
			contentAlignment = Alignment.Center
		) {
			AsyncImage(
				modifier = Modifier.matchParentSize(),
				model = state.onboardingInfo.data.coursesCoverUrls[pageIndex],
				contentDescription = null,
				contentScale = ContentScale.FillBounds
			)
		}
	}
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun OnboardingKnowledgeFromMastersPreview() {
	Column {
		OnboardingKnowledgeFromMasters(
			columnScope = this,
			state = OnboardingState(
				step = OnboardingState.Step.KNOWLEDGE_FROM_MASTERS
			)
		)
	}
}