package com.linhcn.simplenoteapp.domain.time

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DateTimeUtil {

    private val timeZone: TimeZone = TimeZone.currentSystemDefault();

    fun now(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(timeZone)
    }

    fun toEpochMillis(dateTime: LocalDateTime): Long {
        return dateTime.toInstant(timeZone).toEpochMilliseconds()
    }
}