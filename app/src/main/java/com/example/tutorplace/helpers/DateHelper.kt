package com.example.tutorplace.helpers

import java.time.ZonedDateTime
import java.util.Date

object DateHelper {

	fun ZonedDateTime.toDate(): Date = Date.from(this.toInstant())
}