package com.example.tutorplace.ui.base

import androidx.compose.runtime.Immutable

@Immutable
data class DataInfo<out T>(
	val items: List<T> = emptyList(),
	val isLoading: Boolean = true,
	val throwable: Throwable? = null
)

fun <T> DataInfo<T>.getLoadingStatus(): DataInfo<T> {
	return this.copy(
		isLoading = true,
		items = listOf(),
		throwable = null
	)
}

fun <T> DataInfo<T>.getLoadedStatus(items: List<T>): DataInfo<T> {
	return this.copy(
		isLoading = false,
		items = items,
		throwable = null
	)
}

fun <T> DataInfo<T>.getFailedStatus(throwable: Throwable): DataInfo<T> {
	return this.copy(
		isLoading = false,
		items = listOf(),
		throwable = throwable
	)
}