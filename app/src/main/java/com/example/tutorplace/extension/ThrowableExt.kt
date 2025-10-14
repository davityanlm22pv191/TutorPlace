package com.example.tutorplace.extension

import android.util.Log

fun Throwable.getErrorMessage(): String {
	Log.d("Throwable", this.message.orEmpty())
	return "Произошла неизвестная ошибка"
}