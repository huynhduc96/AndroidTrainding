package com.example.android.marsrealestate.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.marsrealestate.data.model.DatabaseImage

@Dao
interface ImageDao {
  @Query("select * from image_table")
  fun getImages(): LiveData<List<DatabaseImage>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(images: List<DatabaseImage>)
}
