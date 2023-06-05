package com.gogumac.climbup.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gogumac.climbup.Level
import java.sql.Date
import java.time.LocalDateTime

@Entity(tableName = "record")
data class ClimbingRecord(
    @PrimaryKey(autoGenerate = true) val id:Int?=null,
    val date: LocalDateTime,
    //val level: Level,
    var videoPath:String?=null,
    val loacation:String?=null
)