package com.example.moviedb.screen.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviedb.data.model.Genre
import com.example.moviedb.data.repository.impl.UserRepositoryImpl
import com.example.moviedb.screen.base.BaseViewModel
import kotlinx.coroutines.*

class HomeViewModel(private val repositoryImpl: UserRepositoryImpl) : BaseViewModel() {
    // Define enum Loading type
    enum class LoadingApiStatus { LOADING, ERROR, DONE }

    // Define Job and Coroutine
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    // Define LiveData status loading
    private val _status = MutableLiveData<LoadingApiStatus>()
    val status: LiveData<LoadingApiStatus>
        get() = _status

    // Define LiveData set event network
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private val _listGenres = MutableLiveData<List<Genre>>()
    val listGenres: MutableLiveData<List<Genre>>
        get() = _listGenres

    init {
        getFirstInfoForApp()
    }

    /**
     * Sets the value of the status LiveData to the API status.
     */
    private fun getFirstInfoForApp() {
        coroutineScope.launch {
            try {
                _status.value = LoadingApiStatus.LOADING
                withContext(Dispatchers.IO) {
                    val listGenre = repositoryImpl.getGenreList().genres ?: emptyList()
                    listGenres.postValue(listGenre)
                }
                _status.value = LoadingApiStatus.DONE
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (e: Exception) {
                _status.value = LoadingApiStatus.ERROR
                _eventNetworkError.value = true
            }
        }
    }

    fun refreshData() {
        coroutineScope.launch {
            try {
                _status.value = LoadingApiStatus.LOADING
                withContext(Dispatchers.IO) {
                    val listGenre = repositoryImpl.getGenreList().genres ?: emptyList()
                    listGenres.postValue(listGenre)
                }
                _status.value = LoadingApiStatus.DONE
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (e: Exception) {
                _status.value = LoadingApiStatus.ERROR
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
}
