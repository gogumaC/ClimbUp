package com.gogumac.climbup.utils

import android.util.Log
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDateTime

object SimpleCalendar {
        const val DAY_START=0
        const val DAY_FINISH=1

        const val MILLISEC=1
        const val SEC= MILLISEC*1000
        const val MINUTE=SEC*60
        const val HOUR= MINUTE*60
        const val DAY=HOUR*24

    fun generateLocalDateTime(year:Int, month:Int,day:Int,timeOption:Int= DAY_START):LocalDateTime{
        return try{
            if(timeOption== DAY_START)LocalDateTime.of(year,month,day,0,0,0)
            else LocalDateTime.of(year,month,day,23,59,59)
        }catch(e: Exception){
            //throw IllegalArgumentException("A percentage value must be between 0 and 100: ${percentage}")
            LocalDateTime.now()
        }


    }

    fun getDayStart(day:Long):Long=day/HOUR*HOUR

    fun getDayEnd(day:Long):Long=day/HOUR*HOUR+DAY-1

//    fun generateDateWithTime(year:Int, month:Int,day:Int,hour:Int=0,min:Int=0,sec:Int=0):Long{
//        if(hour==0&&min==0&&sec==0) return generateDate(year,month,day)
//    }
}