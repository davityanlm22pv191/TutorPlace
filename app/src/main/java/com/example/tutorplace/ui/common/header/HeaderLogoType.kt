package com.example.tutorplace.ui.common.header

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class HeaderLogoType {

	data class Image(
		@param:DrawableRes val image: Int,
		val paddingTop: Int = 40
	) : HeaderLogoType()

	data class Text(@param:StringRes val text: Int) : HeaderLogoType()

	data object None : HeaderLogoType()
}