package com.example.moviedb.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviedb.utils.Constant
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val adult: Boolean? = false,
    @Json(name = "backdrop_path")
    val backdropPath: String? = null,
    val budget: Int? = null,
    val homepage: String? = null,
    @Json(name = "imdb_id")
    val imdbId: String? = null,
    @Json(name = "original_language")
    val originalLanguage: String? = null,
    @Json(name = "original_title")
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    @Json(name = "poster_path")
    val posterPath: String? = null,
    @Json(name = "release_date")
    val releaseDate: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = false,
    @Json(name = "vote_average")
    val voteAverage: Float? = null,
    @Json(name = "vote_count")
    val voteCount: Int? = null,
    var isFavorite: Boolean? = false,
    val genres: List<Genre>? = null,
    @Json(name = "credits")
    var credits: Casts? = null,
    @Json(name = "videos")
    val videos: Trailers? = null
) : Parcelable {

    fun getFullBackdropPath() = Constant.LARGE_IMAGE_URL + backdropPath

    fun getFullPosterPath() = Constant.SMALL_IMAGE_URL + posterPath
}
