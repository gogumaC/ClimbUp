package com.gogumac.climbup.room

import androidx.room.Dao
import androidx.room.Query
import java.sql.Date
import java.time.LocalDateTime

@Dao
interface ClimbingRecordDao:BaseDao<ClimbingRecord> {


    @Query("SELECT * FROM record")
    override fun getAll(): List<ClimbingRecord>

    @Query("SELECT * FROM record WHERE date BETWEEN :monthStart AND :monthEnd")
    fun getMonthRecords(monthStart:Long,monthEnd:Long):List<ClimbingRecord>

    @Query("SELECT * FROM record WHERE date BETWEEN :dayStart AND :dayEnd")
    fun getDayRecords(dayStart:LocalDateTime,dayEnd:LocalDateTime):List<ClimbingRecord>
}