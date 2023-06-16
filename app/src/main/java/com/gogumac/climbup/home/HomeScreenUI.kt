package com.gogumac.climbup.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
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
import com.gogumac.climbup.R
import com.gogumac.climbup.room.ClimbingRecord
import com.gogumac.climbup.ui.theme.ClimbUpTheme


class HomeScreenUI {


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HomeScreen(
        modifier: Modifier = Modifier,
        //climbingRecViewModel: ClimbingRecordViewModel = viewModel()
    ){

        //val dayUiState by climbingRecViewModel.dayUiState.collectAsState()
        //var year by rememberSaveable { mutableStateOf<Int>(0) }
        ClimbUpTheme {
            // A surface container using the 'background' color from the theme

//            val onDateChanged:(MaterialCalendarView, CalendarDay, Boolean)->Unit={view,date,selected->
//                climbingRecViewModel.getDayRecords(date)
//            }
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {

                Scaffold(
                    modifier=modifier,
                    topBar = {HomeTopAppbar()}
                ){paddingValues ->
                    Column(modifier= Modifier.padding(paddingValues)) {
                        Summary()
                        ClimbUpCalendar()//onDateChanged=onDateChanged)
                        //if(!dayUiState.isEmpty){
                        //    ClimbingRecord(date = dayUiState.date, records = dayUiState.records)
                        //}

                    }
                }



            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HomeTopAppbar(){
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
    fun BasicCard(
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
    fun Summary(modifier: Modifier = Modifier){
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
    fun ClimbUpCalendar(
        modifier: Modifier = Modifier,
        //onDateChanged:(MaterialCalendarView, CalendarDay, Boolean)->Unit,
        //climbingRecViewModel: ClimbingRecordViewModel = viewModel()
    ){
//        val currentMonth = remember { YearMonth.now() }
//        val startMonth = remember { currentMonth.minusMonths(100) } // Adjust as needed
//        val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
//        val firstDayOfWeek = remember { firstDayOfWeekFromLocale() } // Available from the library
//
//        val state = rememberCalendarState(
//            startMonth = startMonth,
//            endMonth = endMonth,
//            firstVisibleMonth = currentMonth,
//            firstDayOfWeek = firstDayOfWeek,
//            outDateStyle = OutDateStyle.EndOfRow
//        )
        //val monthUiState by climbingRecViewModel.monthUiState.collectAsState()
        BasicCard(
            modifier= modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 15.dp, end = 15.dp)
        ) {


//            HorizontalCalendar(
//                modifier= Modifier.padding(10.dp),
//                state=state,
//                dayContent = { Day(day=it) },
//                monthHeader =  { month ->
//
//                    val daysOfWeek = month.weekDays.first().map { it.date.dayOfWeek }
//                    MonthHeader(daysOfWeek = daysOfWeek,month=month)
//                }
//            )
            //ㅅ----------------------------------------------------------------
//            AndroidView(
//                { MaterialCalendarView(it) },
//                modifier=modifier.fillMaxWidth(),
//                update={views->
//                    views.setOnDateChangedListener { widget, date, selected ->
//                        onDateChanged(widget,date,selected) }
//                    views.setOnMonthChangedListener { widget, date ->
//                        climbingRecViewModel.getMonthRecords(date)
//                    }
//                    monthUiState.records.forEach {
//                        //views.
//                        Log.d("MONTH",it.toString())
//                    }
//                }
//
//            )
        }
    }


//    @Composable
//    private fun MonthHeader(month: CalendarMonth, daysOfWeek: List<DayOfWeek>) {
//        Column(
//            modifier= Modifier
//        ){
//            Text("${month.yearMonth.year}년 ${month.yearMonth.month.value}월 기록")
//            Spacer(modifier= Modifier.height(8.dp))
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .testTag("MonthHeader"),
//            ) {
//                for (dayOfWeek in daysOfWeek) {
//                    Text(
//                        modifier = Modifier.weight(1f),
//                        textAlign = TextAlign.Center,
//                        fontSize = 15.sp,
//                        text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
//                        fontWeight = FontWeight.Medium,
//                    )
//                }
//            }
//        }
//
//    }


//    @Composable
//    fun Day(
//        modifier: Modifier = Modifier,
//        day: CalendarDay,
//    ){
//        Box(
//            modifier = Modifier
//                .aspectRatio(1f), // This is important for square sizing!
//            contentAlignment = Alignment.Center
//        ) {
//            Text(text = day.date.dayOfMonth.toString())
//        }
//    }


    @Composable
    fun ClimbingRecord(
        modifier: Modifier = Modifier,
        date: String = "0000-00-00",
        records:List<ClimbingRecord> = listOf()
    ){
        Column(
            modifier=modifier.padding( start = 15.dp, end = 15.dp)
        ) {
            Row(
                modifier= modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text=date,
                    modifier= Modifier.align(Alignment.Bottom)
                )
                TextButton(
                    modifier = modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .align(Alignment.Bottom),
                    contentPadding= PaddingValues(),

                    onClick = {}
                ) {
                    Text(
                        stringResource(id = R.string.detail),
                        modifier=modifier.align(Alignment.Bottom)
                    )
                }
            }
            BasicCard(
                modifier=modifier.fillMaxWidth()
            ) {
                LazyVerticalGrid(
                    modifier=modifier.padding(3.dp),
                    columns = GridCells.Adaptive(minSize=45.dp),
                    contentPadding= PaddingValues(horizontal=5.dp,vertical=1.dp)
                ){
                    //다른 item을 가져옴에 유의
                    items(records){record->
                        Text(text=record.level.text)
                    }
                }
            }
        }

    }


    @Composable
    fun LevelIcon(modifier: Modifier = Modifier){
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
    fun HomeScreenPreview(){
        HomeScreen()
    }


}