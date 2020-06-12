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
    val backdrop_path: String? = null,
    val budget: Int? = null,
    val homepage: String? = null,
    val imdb_id: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = false,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
    var isFavorite: Boolean? = false,
    val genres: List<Genre>? = null,
    @Json(name = "credits")
    var credits: ListCast? = null,
    @Json(name = "videos")
    val videos: ListTrailer? = null
) : Parcelable, Serializable {

    fun getFullBackdropPath() =
        if (backdrop_path.isNullOrBlank()) null else Constant.LARGE_IMAGE_URL + backdrop_path

    fun getFullPosterPath() =
        if (poster_path.isNullOrBlank()) null else Constant.SMALL_IMAGE_URL + poster_path
}
