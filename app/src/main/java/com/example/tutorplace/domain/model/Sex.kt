package com.example.tutorplace.domain.model

enum class Sex {
	MALE,
	MIDDLE,
	FEMALE;
}

fun Sex.switchStringResId(
	maleStringResId: Int,
	middleStringResId: Int,
	femaleStringResId: Int
): Int {
	return when (this) {
		Sex.MALE -> maleStringResId
		Sex.MIDDLE -> middleStringResId
		Sex.FEMALE -> femaleStringResId
	}
}