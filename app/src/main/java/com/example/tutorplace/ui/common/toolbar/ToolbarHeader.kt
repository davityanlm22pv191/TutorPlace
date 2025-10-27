package com.example.tutorplace.ui.common.toolbar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tutorplace.R
import com.example.tutorplace.ui.common.CircleBadgeCounter
import com.example.tutorplace.ui.theme.Black16
import com.example.tutorplace.ui.theme.Black36
import com.example.tutorplace.ui.theme.Black49
import com.example.tutorplace.ui.theme.GreyD5
import com.example.tutorplace.ui.theme.GreyF8
import com.example.tutorplace.ui.theme.PurpleDE
import com.example.tutorplace.ui.theme.Red33
import com.example.tutorplace.ui.theme.Transparent
import com.example.tutorplace.ui.theme.Typography
import com.example.tutorplace.ui.theme.White
import com.example.tutorplace.ui.theme.Yellow12

@Composable
fun ToolbarHeader(
	modifier: Modifier = Modifier,
	screenName: String,
	unreadEmailCount: Int,
	profileImageUrl: String,
	level: Int,
	progress: Float,
	isArrowVisible: Boolean,
	isLoading: Boolean,
	isTransparentBackground: Boolean = false,
	isLightAppearance: Boolean = true,
	onBackClicked: () -> Unit = {},
	onNotificationClicked: () -> Unit,
	onSearchClicked: () -> Unit,
	onProfileClicked: () -> Unit,
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.wrapContentHeight()
			.background(
				color = if (isTransparentBackground) Transparent else White,
				shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
			)
			.statusBarsPadding()
			.padding(horizontal = 16.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		AnimatedContent(
			targetState = isArrowVisible,
			label = "arrow",
		) {
			if (it) {
				Icon(
					modifier = Modifier
						.size(24.dp)
						.clip(CircleShape)
						.clickable(interactionSource = null, indication = null) { onBackClicked() },
					painter = painterResource(R.drawable.ic_arrow_left_black_16),
					contentDescription = null,
					tint = if (isLightAppearance) Black16 else White
				)
			}
		}
		Spacer(Modifier.width(4.dp))
		Image(
			modifier = Modifier.size(28.dp),
			painter = painterResource(R.drawable.ic_tutor_place_logo),
			contentDescription = null
		)
		Spacer(Modifier.width(12.dp))
		AnimatedContent(targetState = screenName, label = "screenName") {
			Text(
				text = it,
				style = Typography.titleMedium.copy(color = if (isLightAppearance) Black16 else White)
			)
		}
		Spacer(Modifier.weight(1f))
		Box(
			modifier = Modifier
				.size(44.dp)
				.clip(CircleShape)
				.clickable { onNotificationClicked() }
		) {
			Icon(
				modifier = Modifier.align(Alignment.Center),
				painter = painterResource(R.drawable.ic_email),
				contentDescription = null,
				tint = if (isLightAppearance) Black16 else White
			)
			AnimatedContent(
				modifier = Modifier.align(Alignment.TopEnd),
				targetState = unreadEmailCount,
				label = "unreadEmailCount"
			) {
				if (it > 0) {
					CircleBadgeCounter(
						modifier = Modifier
							.align(Alignment.TopEnd)
							.offset(x = (-5).dp, y = 6.dp),
						value = unreadEmailCount,
						badgeColor = Red33,
						textColor = White
					)
				}
			}
		}
		Box(
			modifier = Modifier
				.size(44.dp)
				.clip(CircleShape)
				.clickable { onSearchClicked() },
			contentAlignment = Alignment.Center
		) {
			Icon(
				painter = painterResource(R.drawable.ic_search),
				contentDescription = null,
				tint = if (isLightAppearance) Black16 else White
			)
		}
		Surface(
			modifier = Modifier,
			color = if (isLightAppearance) GreyF8 else Black36,
			shape = RoundedCornerShape(40.dp),
			onClick = { onProfileClicked() }
		) {
			Row(
				modifier = Modifier.padding(2.dp, end = 4.dp),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.spacedBy(4.dp)
			) {
				ProfileWithProgressAndLevel(
					profileImageUrl,
					progress,
					level,
					isLoading,
					isLightAppearance
				)
				Icon(
					modifier = Modifier.rotate(270f),
					painter = painterResource(R.drawable.ic_arrow_down_black_16),
					contentDescription = null,
					tint = if (isLightAppearance) Black16 else White
				)
			}
		}
	}
}

@Composable
private fun ProfileWithProgressAndLevel(
	profileImageUrl: String,
	progress: Float,
	level: Int,
	isLoading: Boolean,
	isLightAppearance: Boolean
) {
	Box(
		modifier = Modifier
			.wrapContentWidth()
			.padding(top = 2.dp, end = 4.dp)
			.padding(bottom = 2.dp)
	) {
		if (isLoading) {
			CircularProgressIndicator(
				modifier = Modifier
					.size(28.dp)
					.align(Alignment.Center),
				trackColor = if (isLightAppearance) GreyD5 else Black49,
				strokeWidth = 2.dp,
				color = PurpleDE,
				gapSize = 0.dp
			)
		} else {
			CircularProgressIndicator(
				modifier = Modifier
					.size(28.dp)
					.align(Alignment.Center),
				progress = { progress },
				trackColor = GreyD5,
				strokeWidth = 2.dp,
				color = PurpleDE,
				gapSize = 0.dp
			)
		}
		var isLoaded by remember { mutableStateOf(false) }
		val alpha by animateFloatAsState(targetValue = if (isLoaded) 1f else 0f)
		AsyncImage(
			modifier = Modifier
				.size(24.dp)
				.align(Alignment.Center)
				.clip(CircleShape)
				.alpha(alpha),
			model = profileImageUrl,
			contentDescription = null,
			placeholder = ColorPainter(GreyD5),
			onSuccess = { isLoaded = true }
		)
		AnimatedContent(
			modifier = Modifier
				.align(Alignment.TopEnd)
				.offset(x = 4.dp, y = (-2).dp),
			targetState = level,
			label = "level"
		) {
			if (level != 0) {
				CircleBadgeCounter(
					value = it,
					badgeColor = Yellow12,
					textColor = Black16
				)
			}
		}
	}
}

@Preview(showBackground = true, backgroundColor = 0xFFF8F8F8)
@Composable
fun ToolbarHeaderPreview() {
	Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
		Surface(
			color = Black16,
			shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
		) {
			ToolbarHeader(
				screenName = "Уроки",
				unreadEmailCount = 0,
				profileImageUrl = "",
				level = 9,
				progress = 1f,
				isArrowVisible = true,
				isLoading = false,
				isTransparentBackground = true,
				isLightAppearance = false,
				onBackClicked = {},
				onNotificationClicked = {},
				onSearchClicked = {},
				onProfileClicked = {},
			)
		}
		ToolbarHeader(
			screenName = "Моe обучение",
			unreadEmailCount = 2,
			profileImageUrl = "",
			level = 2,
			progress = 0.25f,
			isArrowVisible = false,
			isLoading = false,
			onBackClicked = {},
			onNotificationClicked = {},
			onSearchClicked = {},
			onProfileClicked = {},
		)
		ToolbarHeader(
			screenName = "Дом",
			unreadEmailCount = 99,
			profileImageUrl = "",
			level = 0,
			progress = 0.9f,
			isArrowVisible = false,
			isLoading = false,
			onBackClicked = {},
			onNotificationClicked = {},
			onSearchClicked = {},
			onProfileClicked = {},
		)
		ToolbarHeader(
			screenName = "Уроки",
			unreadEmailCount = 999,
			profileImageUrl = "",
			level = 9,
			progress = 1f,
			isArrowVisible = true,
			isLoading = true,
			onBackClicked = {},
			onNotificationClicked = {},
			onSearchClicked = {},
			onProfileClicked = {},
		)
	}
}