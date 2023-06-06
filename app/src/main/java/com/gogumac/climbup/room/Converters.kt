package com.gogumac.climbup.room

import androidx.room.TypeConverter
import com.gogumac.climbup.Level
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.TimeZone


class Converters {

    @TypeConverter
    fun toLocalDateTime(timestamp:Long?):LocalDateTime?{
        timestamp?.let {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp),TimeZone.getDefault().toZoneId())
        }
        return null
    }

    @TypeConverter
    fun fromLocalDateTime(ldt:LocalDateTime?):Long?{
        return ldt?.atZone(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()
    }

    @TypeConverter
    fun fromLevel(level: Level):String{
        return level.text
    }

    @TypeConverter
    fun toLevel(level:String):Level{
        return Level(level)
    }


}