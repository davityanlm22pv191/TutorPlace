package com.example.tutorplace.ui.screens.onboarding.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorplace.R
import com.example.tutorplace.domain.model.DataInfo
import com.example.tutorplace.ui.common.SkeletonShimmer
import com.example.tutorplace.ui.common.spannabletext.SpanClickableText
import com.example.tutorplace.ui.common.spannabletext.SpanLinkData
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState
import com.example.tutorplace.ui.theme.Black16
import com.example.tutorplace.ui.theme.GreyD5
import com.example.tutorplace.ui.theme.PurpleCC
import com.example.tutorplace.ui.theme.Typography

@Composable
fun OnboardingQuizzes(
	state: OnboardingState.Quizzes,
	columnScope: ColumnScope,
) = with(columnScope) {
	AnimatedContent(
		targetState = state.productName,
	) { productName ->
		if (productName.data.isNotEmpty() && !state.isLoading) {
			SpanClickableText(
				modifier = Modifier
					.fillMaxWidth()
					.padding(16.dp),
				text = stringResource(
					R.string.onboarding_quizzes_new_product_is_available_format,
					productName.data
				),
				links = listOf(
					SpanLinkData(
						link = productName.data,
						tag = productName.data,
						style = SpanStyle(color = PurpleCC),
						onClick = {}
					)
				),
				textStyle = Typography.labelMedium.copy(color = Black16, textAlign = Center)
			)
		} else {
			SkeletonShimmer { Skeleton() }
		}
	}
	Text(
		modifier = Modifier
			.fillMaxWidth()
			.padding(top = 16.dp)
			.padding(horizontal = 16.dp),
		text = stringResource(R.string.onboarding_quizzes_this_only_begin),
		style = Typography.labelMedium.copy(color = Black16, textAlign = Center),
		softWrap = true,
	)
}

@Composable
private fun Skeleton() {
	Column(
		modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
		verticalArrangement = Arrangement.spacedBy(4.dp),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Box(
			Modifier
				.fillMaxWidth()
				.height(16.dp)
				.clip(RoundedCornerShape(16.dp))
				.drawWithCache {
					onDrawBehind {
						val purpleHalfWidth = size.width / 5
						drawRect(
							color = lerp(PurpleCC, GreyD5, 0.5f),
							size = Size(purpleHalfWidth, size.height)
						)
						drawRect(
							color = GreyD5,
							size = Size(size.width * 0.8f, size.height),
							topLeft = Offset(purpleHalfWidth, 0f)
						)
					}
				}
		)
		Box(
			Modifier
				.width(160.dp)
				.height(16.dp)
				.background(GreyD5, shape = RoundedCornerShape(16.dp))
		)
	}
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun OnboardingQuizzesPreview() {
	Column {
		OnboardingQuizzes(
			columnScope = this,
			state = OnboardingState.Quizzes(
				productName = DataInfo(data = "Астрология"),
			)
		)
	}
}