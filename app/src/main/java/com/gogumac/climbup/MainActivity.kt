package com.gogumac.climbup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gogumac.climbup.home.HomeScreen

import com.gogumac.climbup.ui.theme.ClimbUpTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClimbUpApp()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ClimbUpApp(
        modifier: Modifier=Modifier,

    ){

        ClimbUpTheme {
            HomeScreen()
            //TestScreen()
        }
    }


    @Preview
    @Composable
    fun ClimbUpAppPreview(){
        ClimbUpApp()
    }

}

