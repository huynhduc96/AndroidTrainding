package com.example.android.marsrealestate.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.marsrealestate.data.model.MarsProperty
import com.example.android.marsrealestate.data.local.ImagesDatabase
import com.example.android.marsrealestate.data.service.MarsApi
import com.example.android.marsrealestate.data.service.MarsApiFilter
import com.example.android.marsrealestate.utils.asDatabaseModel
import com.example.android.marsrealestate.utils.asImageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository for fetching images from the network and storing them on disk
 */

class ImagesRepository(application: Application) {
  private val database: ImagesDatabase

  init {
    database = ImagesDatabase.getDatabase(application)
  }

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

  val images: LiveData<List<MarsProperty>> = Transformations.map(database.ImageDao.getImages()) {
    it.asImageModel()
  }

}