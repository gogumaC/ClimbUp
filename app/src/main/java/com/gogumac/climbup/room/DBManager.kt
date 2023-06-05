package com.gogumac.climbup.room

import androidx.room.Room
import com.gogumac.climbup.Climup

object DBManager {

    val db= Room.databaseBuilder(
        Climup.ApplicationContext(),
        ClimbUpDatabase::class.java,"climbup"
    ).build()


}