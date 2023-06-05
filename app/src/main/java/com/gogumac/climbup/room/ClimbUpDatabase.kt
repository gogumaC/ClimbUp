package com.gogumac.climbup.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities=[ClimbingRecord::class],version=1)
@TypeConverters(Converters::class)
abstract class ClimbUpDatabase:RoomDatabase() {
    abstract fun climbingRecordDao():ClimbingRecordDao
}