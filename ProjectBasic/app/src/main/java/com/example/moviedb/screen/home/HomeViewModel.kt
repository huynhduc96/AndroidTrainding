package com.example.moviedb.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.example.moviedb.data.model.Genre
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.repository.impl.UserRepositoryImpl
import com.example.moviedb.screen.base.BaseViewModel
import com.example.moviedb.utils.Constant.DEFAULT_FIRST_GENRES
import com.example.moviedb.utils.Constant.DEFAULT_FIRST_PAGE
import kotlinx.coroutines.*

class HomeViewModel(private val repositoryImpl: UserRepositoryImpl) : BaseViewModel() {
    // Define enum Loading type
    enum class LoadingApiStatus { LOADING, ERROR, DONE }

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

    private val _navigateToSelectedMovie = MutableLiveData<Movie>()
    val navigateToSelectedMovie: LiveData<Movie>
        get() = _navigateToSelectedMovie

    private val _listGenres = MutableLiveData<List<Genre>>()
    val listGenres: LiveData<List<Genre>>
        get() = _listGenres
    private var currentPage = DEFAULT_FIRST_PAGE
    val genresSelected = MutableLiveData<Int>()
    private val _listMovies = MutableLiveData<MutableList<Movie>>()
    val listMovies: LiveData<MutableList<Movie>>
        get() = _listMovies

    init {
        getFirstInfoForApp()
        getMovieData(DEFAULT_FIRST_GENRES, false)
    }

    /**
     * Sets the value of the status LiveData to the API status.
     */
    private fun getFirstInfoForApp() {
        viewModelScope.launch {
            try {
                _status.value = LoadingApiStatus.LOADING
                withContext(Dispatchers.IO) {
                    val listGenre = repositoryImpl.getGenreList().genres ?: emptyList()
                    _listGenres.postValue(listGenre)
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

    fun getMovieData(genre: Int, isSwitchTab: Boolean) {
        if (isSwitchTab) currentPage = DEFAULT_FIRST_PAGE
        viewModelScope.launch {
            try {
                _status.value = LoadingApiStatus.LOADING
                withContext(Dispatchers.IO) {
                    val listMovie = repositoryImpl.getMovieList(DEFAULT_FIRST_PAGE, genre).results
                    _listMovies.postValue(listMovie as MutableList<Movie>?)
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
        genresSelected.value?.let { getMovieData(it, true) }
    }

    fun loadMoreData() {
        viewModelScope.launch {
            try {
                currentPage++
                _status.value = LoadingApiStatus.LOADING
                withContext(Dispatchers.IO) {
                    val listMovie = listGenres.value?.get(0)?.genresID?.let {
                        repositoryImpl.getMovieList(
                            currentPage,
                            it
                        ).results
                    }
                    val newList = listMovies.value
                    newList?.addAll(listMovie!!)
                    _listMovies.postValue(newList)
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

    fun displayMovieDetails(movie: Movie) {
        _navigateToSelectedMovie.value = movie
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedMovie.value = null
    }

    /**
     * Resets the network error flag.
     */
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}
