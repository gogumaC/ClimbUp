package com.gogumac.climbup.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities=[ClimbingRecord::class],version=1)
//@TypeConverter(Converter::class)
abstract class ClimbUpDatabase:RoomDatabase() {
    abstract fun climbingRecordDao():ClimbingRecordDao
}