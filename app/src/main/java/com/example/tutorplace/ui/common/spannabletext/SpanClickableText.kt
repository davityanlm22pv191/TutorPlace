package com.example.tutorplace.ui.common.spannabletext

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.example.tutorplace.ui.theme.Typography
import kotlin.compareTo

@Composable
fun SpanClickableText(
	modifier: Modifier = Modifier,
	text: String,
	links: List<SpanLinkData>,
	textStyle: TextStyle,
) {
	val annotatedText = buildSpannableString(text, links)
	Text(
		modifier = modifier,
		text = annotatedText,
		style = textStyle
	)
}

@Composable
private fun buildSpannableString(
	text: String,
	links: List<SpanLinkData>
): AnnotatedString {
	return buildAnnotatedString {
		append(text)
		links.forEach { (link, tag, style, onClick) ->
			val linkStart = link.let { linkHighlight -> text.indexOf(linkHighlight) }
			if (linkStart >= 0) {
				val clickableText = LinkAnnotation.Clickable(
					tag = tag,
					styles = TextLinkStyles(style)
				) {
					onClick()
				}
				addLink(clickableText, linkStart, linkStart + link.length)
			}
		}
	}
}