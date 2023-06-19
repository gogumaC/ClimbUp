package com.gogumac.climbup.home

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gogumac.climbup.ClimbingRecordViewModel
import com.gogumac.climbup.DayUiState
import com.gogumac.climbup.MonthUiState
import com.gogumac.climbup.R
import com.gogumac.climbup.component.Levels.LevelIcon
import com.gogumac.climbup.room.ClimbingRecord
import com.gogumac.climbup.ui.theme.ClimbUpTheme
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import kotlinx.coroutines.flow.StateFlow
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.TimeZone



@Composable
internal fun HomeScreen(
    climbingRecViewModel: ClimbingRecordViewModel = viewModel()
){

    val onDateSelected:(CalendarDay)->Unit={
        climbingRecViewModel.getDayRecords(it.date)
    }
    val onMonthChanged:(CalendarMonth)->Unit={
        Log.d("CHECKFOR","ui "+it.yearMonth.toString())
        climbingRecViewModel.getMonthRecords(it.yearMonth)
    }
    HomeScreen(
        dayUiStateFlow=climbingRecViewModel.dayUiState,
        monthUiStateFlow = climbingRecViewModel.monthUiState,
        onDateSelected = onDateSelected,
        onMonthChanged = onMonthChanged
    )
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    dayUiStateFlow: StateFlow<DayUiState>?=null,
    monthUiStateFlow: StateFlow<MonthUiState>?=null,
    onDateSelected:(CalendarDay)->Unit={},
    onMonthChanged:(CalendarMonth)->Unit={}
){


    ClimbUpTheme {
        // A surface container using the 'background' color from the theme

//            val onDateChanged:(MaterialCalendarView, CalendarDay, Boolean)->Unit={view,date,selected->
//                climbingRecViewModel.getDayRecords(date)
//            }
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            Scaffold(
                modifier=modifier,
                topBar = {HomeTopAppbar()},
            ){paddingValues ->
                Column(
                    modifier= Modifier
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())

                ) {
                    Summary()
                    ClimbUpCalendar(
                        onDateSelected=onDateSelected,
                        onMonthChanged =onMonthChanged,
                        monthUiStateFlow=monthUiStateFlow
                    )
                    if(dayUiStateFlow!=null){
                        ClimbingRecord(
                            dayUiStateFlow = dayUiStateFlow
                        )
                    }

                }
            }



        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopAppbar(){
    TopAppBar(
        title={ Text(text= stringResource(id = R.string.app_name)) },
        navigationIcon={
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }

    )
}

@Composable
private fun BasicCard(
    modifier: Modifier = Modifier,
    content: @Composable ()->Unit
){

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
    ){
        content.invoke()
    }
}

@Composable
private fun Summary(modifier: Modifier = Modifier){
    BasicCard(
        modifier=modifier.padding(top = 15.dp, start = 15.dp, end = 15.dp)
    ) {
        Column(modifier = modifier.padding(10.dp)) {
            Text(stringResource(R.string.home1))
            Text("00"+ stringResource(R.string.home2))
            Text("00"+ stringResource(R.string.home3))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.home4),modifier= Modifier.padding(end=10.dp))
                LevelIcon(modifier= Modifier.size(18.dp))
            }
            ElevatedButton(
                onClick = {  },
                modifier=modifier.fillMaxWidth(),
            ) {
                Text(stringResource(R.string.records_today_climbing))
            }

        }
    }
}

@Composable
private fun ClimbUpCalendar(
    modifier: Modifier = Modifier,
    onDateSelected:(CalendarDay)->Unit={},
    onMonthChanged:(CalendarMonth)->Unit={},
    monthUiStateFlow: StateFlow<MonthUiState>?
){

    val monthUiState=monthUiStateFlow?.collectAsState()

    BasicCard(
        modifier= modifier
            .fillMaxWidth()
            .padding(top = 15.dp, start = 15.dp, end = 15.dp)
    ) {

        com.gogumac.climbup.home.CustomCalendar.ClimbUpCalendar(
            modifier=Modifier.padding(5.dp),
            onDayClicked = { onDateSelected(it) },
            onMonthChanged = { onMonthChanged(it) },
            records = monthUiState?.value?.recordExistList?:setOf()
        )

    }
}


@Composable
private fun ClimbingRecord(
    modifier: Modifier = Modifier,
    dayUiStateFlow: StateFlow<DayUiState>
){

    val dayUiState=dayUiStateFlow.collectAsState().value
    Column(
        modifier=modifier.padding( vertical=10.dp, horizontal = 15.dp)
    ) {
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(
                text=dayUiState.date,
                modifier= Modifier.align(Alignment.Bottom)
            )
            TextButton(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .align(Alignment.Bottom),
                contentPadding= PaddingValues(),

                onClick = {}
            ) {
                Text(
                    stringResource(id = R.string.detail),
                    modifier=Modifier.align(Alignment.Bottom)
                )
            }
        }
        BasicCard(
            modifier=Modifier.fillMaxWidth(),
        ) {
            if(!dayUiState.isEmpty){
                LazyVerticalGrid(
                    modifier=modifier.padding(3.dp),
                    columns = GridCells.Adaptive(minSize=45.dp),
                    contentPadding= PaddingValues(horizontal=5.dp,vertical=1.dp)
                ){
                    //다른 item을 가져옴에 유의
                    items(dayUiState.records){record->
                        Text(text=record.level.text)
                    }
                }
            }else{
                Text("이날은 기록이 없어요")
            }

        }
    }

}


@Composable
private fun LevelIcon(modifier: Modifier = Modifier){
    Canvas(
        modifier=modifier
    ){
        drawCircle(
            color= Color.Cyan,
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview(){
    ClimbUpTheme(
        dynamicColor = false
    ) {
        HomeScreen(modifier = Modifier)
    }

}


