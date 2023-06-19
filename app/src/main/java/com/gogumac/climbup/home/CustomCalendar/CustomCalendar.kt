package com.gogumac.climbup.home.CustomCalendar

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gogumac.climbup.R
import com.gogumac.climbup.ui.theme.ClimbUpTheme
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

/*
* based by kizitonwose/Calendar
*
* */
@Composable
fun ClimbUpCalendar(
    modifier: Modifier=Modifier,
    onDayClicked:(CalendarDay)->Unit,
    onMonthChanged:(CalendarMonth)->Unit,
    records:List<LocalDate> =listOf(LocalDate.now())
){

    val currentMonth = remember{ YearMonth.now()}
    val startMonth=remember{currentMonth.minusMonths(100)}
    val endMonth=remember{currentMonth.plusMonths(100)}
    val firstDayOfWeek=remember{ firstDayOfWeekFromLocale() }
    val daysOfWeek= remember{daysOfWeek()}
    var selectedDate by remember{ mutableStateOf<LocalDate>(LocalDate.now()) }


    val state= rememberCalendarState(
        startMonth=startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek=daysOfWeek.first()
    )


    val onLastMonthClicked:(YearMonth)->Unit={
       // state.scrollToMonth(it) TODO
    }
    val onNextMonthClicked:(YearMonth)->Unit={
        //state.scrollToMonth(it) TODO
    }




    state.firstVisibleMonth.apply{
        onMonthChanged(this)
    }



    Column(modifier=modifier) {
        YearAndMonthTitle(
            calendarMonth = state.firstVisibleMonth,
            onLastMonthClicked = onLastMonthClicked,
            onNextMonthClicked = onNextMonthClicked
        )
        HorizontalCalendar(
            state=state,
            dayContent = {day->
                Day(
                    day=day,
                    isSelected = selectedDate==day.date,
                    onDayClicked = {
                        selectedDate = day.date
                        onDayClicked.invoke(day)
                    },
                    isRecordExist = records.contains(day.date)
                )

            },
            monthHeader = {
                DaysOfWeekTitle(daysOfWeek = daysOfWeek)
            },


        )
    }

}

@Composable
private fun Day(
    day: CalendarDay,
    isSelected:Boolean=false,
    isRecordExist:Boolean=false,
    onDayClicked: (CalendarDay) -> Unit
) {
    Box(
        modifier= Modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(color = if (isSelected) Color.Blue else Color.Transparent)
            .clickable { onDayClicked(day) } ,
        contentAlignment = Alignment.Center,

    ){
        if(isRecordExist){
            Image(
                modifier=Modifier.fillMaxSize(),
                painter=painterResource(id = R.drawable.ic_logo_foreground),
                contentDescription = null
            )
        }
        Text(
            text=day.date.dayOfMonth.toString(),
            color=if (day.position == DayPosition.MonthDate) Color.Black else Color.Gray
        )
    }
}

@Composable
private fun DaysOfWeekTitle(daysOfWeek:List<DayOfWeek>){
    Row(modifier=Modifier.fillMaxWidth()){
        for(dayOfWeek in daysOfWeek){
            Text(
                modifier=Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text=dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
            )
        }
    }
}

@Composable
private fun YearAndMonthTitle(
    calendarMonth: CalendarMonth,
    modifier: Modifier=Modifier,
    onLastMonthClicked:(YearMonth)->Unit,
    onNextMonthClicked:(YearMonth)->Unit
){
    val text=calendarMonth.yearMonth.format(DateTimeFormatter.ofPattern("yyyy.MM"))
    Surface(
        modifier = modifier.padding(16.dp)
    ) {
        Row(
            modifier=Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text=text,
            )
            IconButton(onClick = {onLastMonthClicked(calendarMonth.yearMonth.minusMonths(1))}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.last_month_button)
                )
            }
            IconButton(onClick = { onNextMonthClicked(calendarMonth.yearMonth.plusMonths(1)) }) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = stringResource(id = R.string.next_month_button)
                )
            }
        }

    }


}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
private fun CalendarPreview(){
    ClimbUpTheme(dynamicColor = false) {
        ClimbUpCalendar(
            onDayClicked = {},
            onMonthChanged = {}
        )
    }

}