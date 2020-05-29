package com.example.android.marsrealestate.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android.marsrealestate.data.model.DatabaseImage

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

  companion object {
    @Volatile
    private var INSTANCE: ImagesDatabase? = null

    fun getDatabase(context: Context): ImagesDatabase {
      return INSTANCE ?: synchronized(ImagesDatabase::class.java) {

        val instance = Room.databaseBuilder(context.applicationContext,
            ImagesDatabase::class.java,
            "image_database").build()
        INSTANCE = instance
        instance
      }
    }
  }
}

//@Volatile
//private lateinit var INSTANCE: ImagesDatabase
//
//fun getDatabase(context: Context): ImagesDatabase {
//  synchronized(ImagesDatabase::class.java) {
//    if (!::INSTANCE.isInitialized) {
//      INSTANCE = Room.databaseBuilder(context.applicationContext,
//          ImagesDatabase::class.java,
//          "image_database").build()
//    }
//  }
//  return INSTANCE
//}
