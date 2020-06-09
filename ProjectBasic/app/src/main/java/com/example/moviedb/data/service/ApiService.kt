package com.example.moviedb.data.service

import com.example.moviedb.data.model.GenreResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/3/genre/movie/list")
    suspend fun getGenreAsync(): GenreResponse
}
