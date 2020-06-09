package com.example.moviedb.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
class MovieResponse(
    @Json(name = "page")
    val page: Int? = null,
    @Json(name = "total_results")
    val totalResults: Int? = null,
    @Json(name = "total_pages")
    val totalPages: Int? = null,
    @Json(name = "results")
    var results: List<Movie>? = null
) : Parcelable
