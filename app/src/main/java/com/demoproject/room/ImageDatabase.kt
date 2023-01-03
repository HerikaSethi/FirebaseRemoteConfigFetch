package com.demoproject.room

import android.content.Context
import android.media.Image
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demoproject.model.PicSumResponseItem

@Database(entities = [PicSumResponseItem::class], version = 1)
abstract class ImageDatabase : RoomDatabase(){

    abstract fun roomDao(): RoomDao

    companion object {
        private var INSTANCE: ImageDatabase? = null

        fun getDatabase(context: Context): ImageDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    ImageDatabase::class.java,
                    "imageDb"
                ).build()
            }
            return INSTANCE!!
        }
    }
}