package com.example.moviedb.data.service

import com.example.moviedb.data.model.GenreResponse
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/3/genre/movie/list")
    suspend fun getGenreAsync(): GenreResponse

    @GET("/3/discover/movie")
    suspend fun getPopularMovie(
        @Query("page") page: Int,
        @Query("with_genres") genre: Int
    ): MovieResponse

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("append_to_response") type: String
    ): Movie
}
