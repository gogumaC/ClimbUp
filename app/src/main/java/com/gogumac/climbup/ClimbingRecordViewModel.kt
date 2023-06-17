package com.gogumac.climbup

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.gogumac.climbup.room.ClimbingRecord
import com.gogumac.climbup.room.DBManager.db
import com.gogumac.climbup.utils.SimpleCalendar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.TimeZone

class ClimbingRecordViewModel:ViewModel() {


    private val recordDao=db.climbingRecordDao()


    private val _monthUiState= MutableStateFlow(MonthUiState())
    val monthUiState: StateFlow<MonthUiState> =_monthUiState.asStateFlow()

    private val _dayUiState= MutableStateFlow(DayUiState())
    val dayUiState: StateFlow<DayUiState> =_dayUiState.asStateFlow()

    var calendarRecords=mutableStateListOf<ClimbingRecord>()
        private set

    init{
        testAdd()
        val today=LocalDateTime.now()
        getDayRecords(today.year,today.month.value-1,today.dayOfMonth)
        getMonthRecords(today.year,today.month.value-1)
    }

    fun getDayRecords(timeStamp:Long?){

        val run = Runnable {
            //여기에서 디비관련 작업
            Log.d("DATEDATE",timeStamp.toString())
            val dayStart:LocalDateTime=
                if(timeStamp==null) LocalDate.now().atStartOfDay()
                else LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(timeStamp),
                    TimeZone.getDefault().toZoneId()
                )
            val dayEnd=dayStart.getDayEnd()

            Log.d("DATEDATE","$dayStart  $dayEnd")
            val files=recordDao.getDayRecords(dayStart,dayEnd)
            val dateString=dayStart.toLocalDate().toString()
            _dayUiState.value=DayUiState(dateString, files).apply{
                this.isEmpty=files.isEmpty()
            }

        }
        val thread = Thread(run)
        thread.start()

    }
    fun getDayRecords(year:Int,month:Int,day:Int){
        val run = Runnable {
            //여기에서 디비관련 작업
            val dayStart=SimpleCalendar.generateLocalDateTime(year,month+1,day)
            val dayEnd=SimpleCalendar.generateLocalDateTime(year,month+1,day,SimpleCalendar.DAY_FINISH)
            val files=recordDao.getDayRecords(dayStart,dayEnd)
            val dateString=dayStart.toLocalDate().toString()
            _dayUiState.value=DayUiState(dateString, files).apply{
                this.isEmpty=files.isEmpty()
            }

        }
        val thread = Thread(run)
        thread.start()

    }
//    fun getMonthRecords(date:CalendarDay){
//        getMonthRecords(date.year,date.month)
//    }
    fun getMonthRecords(year:Int,month:Int){
        val run = Runnable {
            //여기에서 디비관련 작업
            val dayStart=SimpleCalendar.generateLocalDateTime(year,month+1,1)
            val dayEnd=dayStart.getLastDayOfMonth()
            val recordDays=recordDao.getDayRecords(dayStart,dayEnd).map{
                it.date.toLocalDate().dayOfMonth
            }.toSet()

            _monthUiState.value=MonthUiState(recordDays).apply{
                this.isEmpty=recordDays.isEmpty()
            }

        }
        val thread = Thread(run)
        thread.start()
    }

    fun testAdd(){
        val run = Runnable {
            //여기에서 디비관련 작업
            recordDao.add(ClimbingRecord(date = LocalDateTime.now(),level=Level("default")))

        }
        val thread = Thread(run)
        thread.start()
        Log.d("RECORD","add record~")
    }

    private fun LocalDateTime.getLastDayOfMonth(): LocalDateTime {
        val date = this.toLocalDate()
        val lastDay = date.withDayOfMonth(date.lengthOfMonth())
        val time = LocalTime.of(23, 59, 59)
        return LocalDateTime.of(lastDay, time)
    }

    private fun LocalDateTime.getDayEnd() : LocalDateTime{
        return this.plusDays(1).minusSeconds(1)
    }



}

data class DayUiState(val date:String="", val records:List<ClimbingRecord> = listOf()):BaseUiState()
data class MonthUiState(val records:Set<Int> =setOf()):BaseUiState()