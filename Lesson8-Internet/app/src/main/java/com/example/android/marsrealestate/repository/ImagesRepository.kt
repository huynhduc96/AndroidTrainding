package com.example.android.marsrealestate.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.marsrealestate.database.ImagesDatabase
import com.example.android.marsrealestate.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository for fetching images from the network and storing them on disk
 */

class ImagesRepository(private val database: ImagesDatabase) {
  suspend fun refreshImage(filter: MarsApiFilter) {
    withContext(Dispatchers.IO) {
      val imageList = MarsApi.retrofitService.getProperties(filter.value).await()
      database.ImageDao.insertAll(imageList.asDatabaseModel())
    }
  }

  suspend fun getnewData(filter: MarsApiFilter) {
    withContext(Dispatchers.IO) {
      val imageList = MarsApi.retrofitService.getProperties(filter.value).await()
      database.ImageDao.insertAll(imageList.asDatabaseModel())
    }
  }

  val images: LiveData<List<MarsProperty>> = Transformations.map(database.ImageDao.getImages()){
    it.asImageModel()
  }

}