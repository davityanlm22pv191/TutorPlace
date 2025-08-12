package com.example.tutorplace.ui.screens.auth.common

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorplace.R
import com.example.tutorplace.ui.theme.Black16
import com.example.tutorplace.ui.theme.Typography

@Composable
fun Header(
	title: String,
	description: String?,
	onBackButtonClicked: (() -> Unit)?
) {
	Box(
		modifier = Modifier.fillMaxWidth()
	) {
		if (onBackButtonClicked != null) {
			Surface(
				modifier = Modifier.padding(top = 16.dp),
				shape = CircleShape,
				color = Color.Transparent,
				onClick = { onBackButtonClicked() }
			) {
				Image(
					painter = painterResource(R.drawable.ic_arrow_left_in_grey_f8_circle),
					contentDescription = null
				)
			}
		}
		Column {
			Image(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 40.dp),
				painter = painterResource(R.drawable.ic_tutor_place_logo),
				contentDescription = null
			)
			Text(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 24.dp),
				text = title,
				style = Typography.headlineLarge,
				color = Black16,
				textAlign = TextAlign.Center
			)
			AnimatedContent(targetState = description) { animatedDescription ->
				if (animatedDescription != null) {
					Text(
						modifier = Modifier
							.fillMaxWidth()
							.padding(top = 8.dp),
						text = animatedDescription,
						style = Typography.labelMedium,
						color = Black16,
						textAlign = TextAlign.Center
					)
				}

			}
		}
	}
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun HeaderPreview() {
	Header(
		title = "Восстановление пароля",
		description = "Введите свой email для восстановления пароля",
		onBackButtonClicked = null
	)
}