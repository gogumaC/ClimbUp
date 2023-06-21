package com.gogumac.climbup

import androidx.compose.ui.graphics.Color

class Level(
    val text:String,
    val level:Int,
    val color: ULong?
    ){

    companion object{

        val testLevel=Level("red",1,Color.Green.value)
        val WAVEROCK_LEVEL=listOf(
            Level("red",1,Color.Red.value),
            Level("orange",2,Color.Red.value),
            Level("yellow",3,Color.Yellow.value),
            Level("green",4,Color.Green.value),
            Level("skyblue",5,Color.LightGray.value),
            Level("navy",6,Color.Blue.value),
            Level("purple",7,Color.Red.value),
            Level("brown",8,Color.Red.value),
            Level("white",9,Color.White.value),
            Level("gray",10,Color.Gray.value),
            Level("black",11,Color.Black.value),

            )
    }

}

