package com.gogumac.climbup.classes

import com.gogumac.climbup.Level

open class Location(
    val name:String,
    val levels:List<Level>,
    val branch:String?=null

    ) {

    fun createBrachLocation(branch: String?):Location{
        return Location(name,levels,branch)
    }

    companion object{
        val WAVEROCK=Location("waverock",levels=Level.WAVEROCK_LEVEL)
        val WAVEROCK_GWANAN= WAVEROCK.createBrachLocation("광안점")
        val WAVEROCK_BUSAN_UNIV= WAVEROCK.createBrachLocation("부산대점")
        val WAVEROCK_SEOMYUN= WAVEROCK.createBrachLocation("서면점")

    }

}







