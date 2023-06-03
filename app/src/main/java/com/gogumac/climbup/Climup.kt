package com.gogumac.climbup

import android.app.Application
import android.content.Context

class Climup:Application() {

    init{
        instance=this
    }

    companion object{
        lateinit var instance:Climup
        fun ApplicationContext(): Context = instance.applicationContext
    }
}