package com.gogumac.climbup

import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.gogumac.climbup.ui.theme.ClimbUpTheme
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClimbUpApp()
        }
    }

    @Composable
    fun ClimbUpApp(modifier: Modifier=Modifier){
        ClimbUpTheme {
            // A surface container using the 'background' color from the theme

            val onDateChanged:(CalendarView,Int,Int,Int)->Unit={view,year,month,dayOfWeek->
                Log.d("checkcheck","$year $month $dayOfWeek")
            }
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column() {
                    Summary()
                    ClimbupCalendar(onDateChanged=onDateChanged)
                }


            }
        }
    }

    @Composable
    fun BasicCard(
        modifier: Modifier=Modifier,
        content: @Composable ()->Unit
    ){
        val dateUpdated={views:CalendarView->
            views.date
        }
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(top=15.dp, start = 15.dp,end=15.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
        ){
            content.invoke()
        }
    }

    @Composable
    fun Summary(modifier: Modifier=Modifier){
        BasicCard(
        ) {
            Column(modifier = modifier.padding(10.dp)) {
                Text(stringResource(R.string.home1))
                Text("00"+stringResource(R.string.home2))
                Text("00"+stringResource(R.string.home3))
                Row() {
                    Text(stringResource(R.string.home4))
                }
                ElevatedButton(
                    onClick = { /*TODO*/ },
                    modifier=modifier.fillMaxWidth(),
                ) {
                    Text(stringResource(R.string.records_today_climbing))
                }

            }
        }
    }

    @Composable
    fun ClimbupCalendar(modifier: Modifier=Modifier,onDateChanged:(CalendarView,Int,Int,Int)->Unit){
        BasicCard(modifier=modifier.fillMaxWidth()) {
            AndroidView(
                { CalendarView(it) },
                modifier=modifier.fillMaxWidth(),
                update={views->
                    views.setOnDateChangeListener { view, year, month, dayOfMonth -> onDateChanged(view,year,month,dayOfMonth) }

                }

            )
        }
    }





    @Preview
    @Composable
    fun ClimbUpAppPreview(){
        ClimbUpApp()
    }

}

