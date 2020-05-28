package com.example.android.marsrealestate.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_table")
data class DatabaseImage constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "imgSrcUrl")
    val imgSrcUrl: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "price")
    val price: Double
)
