package com.gogumac.climbup.room

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.TimeZone


class Converters {

    @TypeConverter
    fun fromTimestamp(timestamp:Long?):LocalDateTime?{
        timestamp?.let {
            return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp),TimeZone.getDefault().toZoneId())
        }
        return null
    }

    @TypeConverter
    fun fromLocalDateTime(ldt:LocalDateTime?):Long?{
        return ldt?.atZone(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()
    }
}