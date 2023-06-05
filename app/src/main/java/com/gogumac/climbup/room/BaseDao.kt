package com.gogumac.climbup.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface BaseDao<T> {

    fun getAll():List<T>

    //편의 메서드
    @Insert
    fun add(vararg : T)

    //편의 메서드
    @Delete
    fun delete(bookmark: T)

}