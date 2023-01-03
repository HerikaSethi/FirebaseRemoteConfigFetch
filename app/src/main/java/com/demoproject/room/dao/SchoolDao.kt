package com.demoproject.room.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.demoproject.room.entities.School

interface SchoolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDirector(school: School)

}