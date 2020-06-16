package com.example.moviedb.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.repository.impl.UserRepositoryImpl
import com.example.moviedb.screen.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val repositoryImpl: UserRepositoryImpl) : BaseViewModel() {
    enum class LoadingApiStatus { LOADING, ERROR, DONE }
    enum class FavoriteStatus { ADD, REMOVE }

    private val _status = MutableLiveData<LoadingApiStatus>()
    val status: LiveData<LoadingApiStatus>
        get() = _status
    private val _statusFavorite = MutableLiveData<FavoriteStatus>()
    val statusFavorite: LiveData<FavoriteStatus>
        get() = _statusFavorite

    // Define LiveData set event network
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown
    private val _movieDetail = MutableLiveData<Movie>()
    val movieDetail: LiveData<Movie>
        get() = _movieDetail

    /**
     * Resets the network error flag.
     */
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    fun getDataMovieDetail(movie: Movie) {
        viewModelScope.launch {
            try {
                _status.value = LoadingApiStatus.LOADING
                withContext(Dispatchers.IO) {
                    val movieResponse = repositoryImpl.getMovieDetail(movie.id, TYPE)
                    _movieDetail.postValue(movieResponse)
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

    fun addAndRemoveFavorite() {
        _movieDetail.value?.let { movie ->
            if (movie.isFavorite!!) {
                movie.isFavorite = false
                deleteMovie(movie)
                _statusFavorite.value = FavoriteStatus.REMOVE
            } else {
                movie.isFavorite = true
                insertMovie(movie)
                _statusFavorite.value = FavoriteStatus.ADD
            }
            _movieDetail.value = movie
        }
    }

    fun getLocalData(movie: Movie) {
        viewModelScope.launch {
            try {
                _status.value = LoadingApiStatus.LOADING
                withContext(Dispatchers.IO) {
                    val result = repositoryImpl.getMovieLocal(movie.id)
                    result?.let {
                        _movieDetail.postValue(result)
                    } ?: run {
                        getDataMovieDetail(movie)
                    }
                }
            } catch (e: Exception) {
                _status.value = LoadingApiStatus.ERROR
                _eventNetworkError.value = true
            }
        }
    }

    private fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            try {
                _status.value = LoadingApiStatus.LOADING
                withContext(Dispatchers.IO) {
                    repositoryImpl.deleteMovieLocal(movie)
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

    private fun insertMovie(movie: Movie) {
        viewModelScope.launch {
            try {
                _status.value = LoadingApiStatus.LOADING
                withContext(Dispatchers.IO) {
                    repositoryImpl.insertMovieLocal(movie)
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

    companion object {
        const val TYPE = "credits,videos"
    }
}
