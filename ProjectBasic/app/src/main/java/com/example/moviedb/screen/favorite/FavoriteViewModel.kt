package com.example.moviedb.screen.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.repository.impl.UserRepositoryImpl
import com.example.moviedb.screen.base.BaseViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavoriteViewModel(private val repositoryImpl: UserRepositoryImpl) : BaseViewModel() {
    enum class LoadingDataBaseStatus { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<LoadingDataBaseStatus>()
    val status: LiveData<LoadingDataBaseStatus>
        get() = _status
    private var _eventDatabaseError = MutableLiveData<Boolean>(false)
    val eventDatabaseError: LiveData<Boolean>
        get() = _eventDatabaseError
    private var _isDatabaseErrorShown = MutableLiveData<Boolean>(false)
    val isDatabaseErrorShown: LiveData<Boolean>
        get() = _isDatabaseErrorShown
    var listMovies: LiveData<List<Movie>>

    init {
        listMovies = repositoryImpl.getMovieListLocal()
    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            try {
                _status.value = LoadingDataBaseStatus.LOADING
                withContext(Dispatchers.IO) {
                    repositoryImpl.deleteMovieLocal(movie)
                }
                _status.value = LoadingDataBaseStatus.DONE
                _eventDatabaseError.value = false
                _isDatabaseErrorShown.value = false
            } catch (e: Exception) {
                _status.value = LoadingDataBaseStatus.ERROR
                _eventDatabaseError.value = true
            }
        }
    }

    fun refreshList() {
        listMovies = repositoryImpl.getMovieListLocal()
    }

    fun onDatabaseErrorShown() {
        _isDatabaseErrorShown.value = true
    }
}
