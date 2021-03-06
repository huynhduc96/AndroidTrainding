/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.screen.overview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.marsrealestate.data.service.MarsApiFilter
import com.example.android.marsrealestate.data.model.MarsProperty
import com.example.android.marsrealestate.data.repository.ImagesRepository
import com.example.android.marsrealestate.data.repository.impl.ImagesRepositoryImpl
import kotlinx.coroutines.*
import java.io.IOException


/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel(private val imagesRepository: ImagesRepositoryImpl) : ViewModel() {
  enum class MarsApiStatus { LOADING, ERROR, DONE }

  // The internal MutableLiveData String that stores the status of the most recent request
  private var viewModelJob = Job()
  private val coroutineScope = CoroutineScope(
      viewModelJob + Dispatchers.Main)


  /**
   * A list of images  displayed on the screen.
   */
  val imagelist = imagesRepository.images()

  // The external immutable LiveData
  private val _status = MutableLiveData<MarsApiStatus>()
  val status: LiveData<MarsApiStatus>
    get() = _status

  private val _properties = MutableLiveData<List<MarsProperty>>()
  val properties: LiveData<List<MarsProperty>>
    get() = _properties

  private val _navigateToSelectedProperty = MutableLiveData<MarsProperty>()
  val navigateToSelectedProperty: LiveData<MarsProperty>
    get() = _navigateToSelectedProperty

  /**
   * Event triggered for network error. This is private to avoid exposing a
   * way to set this value to observers.
   */
  private var _eventNetworkError = MutableLiveData<Boolean>(false)

  /**
   * Event triggered for network error. Views should use this to get access
   * to the data.
   */
  val eventNetworkError: LiveData<Boolean>
    get() = _eventNetworkError

  /**
   * Flag to display the error message. This is private to avoid exposing a
   * way to set this value to observers.
   */
  private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

  /**
   * Flag to display the error message. Views should use this to get access
   * to the data.
   */
  val isNetworkErrorShown: LiveData<Boolean>
    get() = _isNetworkErrorShown

  /**
   * Call getMarsRealEstateProperties() on init so we can display status immediately.
   */
  init {
    getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL)
  }

  /**
   * Sets the value of the status LiveData to the Mars API status.
   */
  fun getMarsRealEstateProperties(filter: MarsApiFilter) {
    coroutineScope.launch {
      try {
        _status.value = MarsApiStatus.LOADING
        withContext(Dispatchers.IO) {
          imagesRepository.getnewData(filter)
        }
        _status.value = MarsApiStatus.DONE
        _eventNetworkError.value = false
        _isNetworkErrorShown.value = false
      } catch (e: Exception) {
        _status.value = MarsApiStatus.ERROR
        _properties.value = ArrayList()
        if (imagelist.value.isNullOrEmpty())
          _eventNetworkError.value = true
      }
    }
  }

  /**
   * Refresh data from the repository. Use a coroutine launch to run in a
   * background thread.
   */
  fun refreshDataFromRepository(filter: MarsApiFilter) {
    coroutineScope.launch {
      try {
        _status.value = MarsApiStatus.LOADING
        withContext(Dispatchers.IO) {
          imagesRepository.refreshImage(filter)
        }
        _status.value = MarsApiStatus.DONE
        _eventNetworkError.value = false
        _isNetworkErrorShown.value = false


      } catch (networkError: IOException) {
        _status.value = MarsApiStatus.ERROR
        // Show a Toast error message and hide the progress bar.
        if (imagelist.value.isNullOrEmpty())
          _eventNetworkError.value = true
      }
    }
  }

  /**
   * Resets the network error flag.
   */
  fun onNetworkErrorShown() {
    _isNetworkErrorShown.value = true
  }


  override fun onCleared() {
    super.onCleared()
    viewModelJob.cancel()
  }

  fun updateFilter(filter: MarsApiFilter) {
    getMarsRealEstateProperties(filter)
  }

  fun displayPropertyDetails(marsProperty: MarsProperty) {
    _navigateToSelectedProperty.value = marsProperty
  }

  fun displayPropertyDetailsComplete() {
    _navigateToSelectedProperty.value = null
  }
}
