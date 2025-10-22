package com.example.tutorplace.ui.common.tagselector.model

import androidx.compose.ui.graphics.Color
import com.example.tutorplace.ui.common.tagselector.model.Tag.UiState
import com.example.tutorplace.ui.theme.Black16
import com.example.tutorplace.ui.theme.GreyF8
import com.example.tutorplace.ui.theme.White

data class Tag(
	val id: String,
	val name: String,
	val isSelected: Boolean = false
) {
	data class UiState(
		val backgroundColor: Color,
		val textColor: Color,
		val isCheckIconVisible: Boolean,
	)
}

fun Tag.getUiState(): UiState {
	return if (this.isSelected) {
		UiState(
			backgroundColor = Black16,
			textColor = White,
			isCheckIconVisible = true
		)
	} else {
		UiState(
			backgroundColor = GreyF8,
			textColor = Black16,
			isCheckIconVisible = false
		)
	}
}