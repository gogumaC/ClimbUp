package com.gogumac.climbup

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.gogumac.climbup.room.ClimbingRecord
import com.gogumac.climbup.room.Converters
import com.gogumac.climbup.room.DBManager.db
import com.gogumac.climbup.utils.SimpleCalendar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime
import java.util.Date

class ClimbingRecordViewModel:ViewModel() {


    private val recordDao=db.climbingRecordDao()


    private val _monthUiState= MutableStateFlow(listOf<ClimbingRecord>())
    val monthUiState: StateFlow<List<ClimbingRecord>> =_monthUiState.asStateFlow()

    private val _dayUiState= MutableStateFlow(DayUiState())
    val dayUiState: StateFlow<DayUiState> =_dayUiState.asStateFlow()

    var calendarRecords=mutableStateListOf<ClimbingRecord>()
        private set

    init{
        testAdd()
        val today=LocalDateTime.now()
        getDayRecords(today.year,today.month.value-1,today.dayOfMonth)
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
    fun getMonthRecords(){

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



}

data class DayUiState(val date:String="", val records:List<ClimbingRecord> = listOf()):BaseUiState()
