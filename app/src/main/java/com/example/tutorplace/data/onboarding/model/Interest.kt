package com.example.tutorplace.data.onboarding.model

import com.example.tutorplace.ui.common.tagselector.model.Tag

data class Interest(val id: Int, val name: String)

fun Interest.toTag(isSelected: Boolean) = Tag(id = id.toString(), name, isSelected)
