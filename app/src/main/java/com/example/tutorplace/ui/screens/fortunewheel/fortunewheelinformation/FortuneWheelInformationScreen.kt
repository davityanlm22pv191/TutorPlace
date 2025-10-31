package com.example.tutorplace.ui.screens.fortunewheel.fortunewheelinformation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tutorplace.R
import com.example.tutorplace.ui.common.PurpleButton
import com.example.tutorplace.ui.common.TransparentButton
import com.example.tutorplace.ui.common.header.Header
import com.example.tutorplace.ui.common.header.HeaderLogoType
import com.example.tutorplace.ui.common.spannabletext.SpanClickableText
import com.example.tutorplace.ui.common.spannabletext.SpanLinkData
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheelinformation.presentation.FortuneWheelInformationState
import com.example.tutorplace.ui.screens.fortunewheel.fortunewheelinformation.presentation.FortuneWheelInformationViewModel
import com.example.tutorplace.ui.theme.Black16
import com.example.tutorplace.ui.theme.ContainerColor
import com.example.tutorplace.ui.theme.PurpleCC
import com.example.tutorplace.ui.theme.ScreenColor
import com.example.tutorplace.ui.theme.Transparent
import com.example.tutorplace.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FortuneWheelInformationScreen(navController: NavController) {
	val viewModel = hiltViewModel<FortuneWheelInformationViewModel>()
	val state by viewModel.state.collectAsState()
	FortuneWheelInformationScreen(
		state,
		onDismissRequest = { navController.popBackStack() },
		onNextClicked = { navController.popBackStack() },
		onMoreAboutPromotionClicked = { navController.popBackStack() }
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FortuneWheelInformationScreen(
	state: FortuneWheelInformationState,
	onDismissRequest: () -> Unit,
	onNextClicked: () -> Unit,
	onMoreAboutPromotionClicked: () -> Unit
) {
	val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
	ModalBottomSheet(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight(),
		containerColor = ScreenColor,
		sheetState = sheetState,
		scrimColor = Transparent,
		onDismissRequest = { onDismissRequest() }
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 16.dp)
				.verticalScroll(rememberScrollState())
				.navigationBarsPadding()
		) {
			Header(
				logo = HeaderLogoType.None,
				title = stringResource(R.string.fortune_wheel),
				description = stringResource(R.string.fortune_wheel_information_description),
				throwable = null,
				onBackButtonClicked = null,
			)
			SpanClickableText(
				modifier = Modifier
					.padding(top = 12.dp)
					.fillMaxWidth(),
				text = stringResource(R.string.fortune_wheel_information_you_may_try_you_luck),
				textStyle = Typography.labelMedium.copy(color = Black16, textAlign = Center),
				links = listOf(
					SpanLinkData(
						tag = "you_may_try_you_luck",
						link = stringResource(R.string.fortune_wheel_information_you_may_try_you_luck_span),
						style = SpanStyle(color = PurpleCC),
						onClick = {}
					)
				),
			)
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 12.dp)
					.background(color = ContainerColor, shape = RoundedCornerShape(20.dp))
					.padding(horizontal = 16.dp, vertical = 20.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Image(
					painter = painterResource(R.drawable.ic_fortune_circle),
					contentDescription = null
				)
				SpanClickableText(
					modifier = Modifier.padding(top = 12.dp),
					text = stringResource(R.string.fortune_wheel_information_every_day_you_are_given_one_free_spin),
					textStyle = Typography.labelMedium.copy(color = Black16),
					links = listOf(
						SpanLinkData(
							tag = "you_may_try_you_luck",
							link = stringResource(R.string.fortune_wheel_information_every_day_you_are_given_one_free_spin_span),
							style = SpanStyle(color = PurpleCC),
							onClick = {}
						)
					)
				)
				SpanClickableText(
					modifier = Modifier.padding(top = 12.dp),
					text = stringResource(R.string.fortune_wheel_information_additional_description),
					textStyle = Typography.labelMedium.copy(
						color = Black16,
						textAlign = Center
					),
					links = listOf(
						SpanLinkData(
							tag = "additional_description",
							link = stringResource(R.string.fortune_wheel_information_additional_description_span),
							style = SpanStyle(color = PurpleCC),
							onClick = {}
						)
					)
				)
			}
			PurpleButton(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 50.dp),
				text = stringResource(R.string.common_next),
				isLoading = false,
				isEnabled = true,
				onClick = { onNextClicked() }
			)
			TransparentButton(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 8.dp, bottom = 16.dp),
				text = stringResource(R.string.fortune_wheel_information_more_about_promotion),
				onClick = { onMoreAboutPromotionClicked() }
			)
		}
	}
}

@Preview
@Composable
fun FortuneWheelInformationScreenPreview() {
	FortuneWheelInformationScreen(
		state = FortuneWheelInformationState,
		onDismissRequest = {},
		onNextClicked = {},
		onMoreAboutPromotionClicked = {}
	)
}
