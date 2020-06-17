package com.example.android.marsrealestate.data.local.db

import androidx.room.*
import com.example.android.marsrealestate.data.local.dao.ImageDao
import com.example.android.marsrealestate.data.model.DatabaseImage


@Database(entities = [DatabaseImage::class], version = 1)
abstract class ImagesDatabase : RoomDatabase() {
  abstract val ImageDao: ImageDao
}
