package com.example.android.marsrealestate.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ImageDao {
  @Query("select * from image_table")
  fun getImages(): LiveData<List<DatabaseImage>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(images: List<DatabaseImage>)
}


@Database(entities = [DatabaseImage::class], version = 1)
abstract class ImagesDatabase : RoomDatabase() {
  abstract val ImageDao: ImageDao
}


private lateinit var INSTANCE: ImagesDatabase

fun getDatabase(context: Context): ImagesDatabase {
  synchronized(ImagesDatabase::class.java) {
    if (!::INSTANCE.isInitialized) {
      INSTANCE = Room.databaseBuilder(context.applicationContext,
          ImagesDatabase::class.java,
          "image_database").build()
    }
  }
  return INSTANCE
}