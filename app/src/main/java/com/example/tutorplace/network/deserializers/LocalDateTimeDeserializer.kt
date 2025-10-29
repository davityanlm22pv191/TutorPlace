package com.example.tutorplace.network.deserializers

import com.example.tutorplace.helpers.FormatHelper.DATE_WITH_TIME
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeDeserializer : JsonDeserializer<LocalDateTime> {

	override fun deserialize(
		json: JsonElement?,
		typeOfT: Type?,
		context: JsonDeserializationContext?
	): LocalDateTime {
		return LocalDateTime.parse(json?.asString, DateTimeFormatter.ofPattern(DATE_WITH_TIME))
	}
}