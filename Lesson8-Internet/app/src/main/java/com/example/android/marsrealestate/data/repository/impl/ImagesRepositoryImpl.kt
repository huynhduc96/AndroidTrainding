package com.example.android.marsrealestate.data.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.marsrealestate.data.local.dao.ImageDao
import com.example.android.marsrealestate.data.model.MarsProperty
import com.example.android.marsrealestate.data.repository.ImagesRepository
import com.example.android.marsrealestate.data.service.MarsApiFilter
import com.example.android.marsrealestate.data.service.MarsApiService
import com.example.android.marsrealestate.utils.asDatabaseModel
import com.example.android.marsrealestate.utils.asImageModel


class ImagesRepositoryImpl(private val imageDao: ImageDao, private val marsApiService: MarsApiService) : ImagesRepository {
  override suspend fun refreshImage(filter: MarsApiFilter) {
    val imageList = marsApiService.getProperties(filter.value).await()
    return imageDao.insertAll(imageList.asDatabaseModel())
  }

  override suspend fun getnewData(filter: MarsApiFilter) {
    val imageList = marsApiService.getProperties(filter.value).await()
    return imageDao.insertAll(imageList.asDatabaseModel())
  }

  override fun images(): LiveData<List<MarsProperty>> {
    return Transformations.map(imageDao.getImages()) {
      it.asImageModel()
    }
  }

}
