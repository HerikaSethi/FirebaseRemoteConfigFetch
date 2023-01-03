package com.demoproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "imagetable")
data class PicSumResponseItem(
    val author: String,
    val download_url: String,
    val height: Int,
    @PrimaryKey
    val id: String,
    val url: String,
    val width: Int
)
