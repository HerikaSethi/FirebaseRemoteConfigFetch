package com.demoproject.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demoproject.model.PicSumResponseItem

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImageData(image: List<PicSumResponseItem>)

    @Query("SELECT * FROM imagetable")
    suspend fun getImageData(): List<PicSumResponseItem>

}