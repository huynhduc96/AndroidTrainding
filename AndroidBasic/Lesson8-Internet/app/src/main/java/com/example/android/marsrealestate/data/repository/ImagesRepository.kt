package com.example.android.marsrealestate.data.repository

import androidx.lifecycle.LiveData
import com.example.android.marsrealestate.data.model.MarsProperty
import com.example.android.marsrealestate.data.service.MarsApiFilter

/**
 * Repository for fetching images from the network and storing them on disk
 */

interface ImagesRepository {

  suspend fun refreshImage(filter: MarsApiFilter)

  suspend fun getnewData(filter: MarsApiFilter)

  fun images(): LiveData<List<MarsProperty>>

}
