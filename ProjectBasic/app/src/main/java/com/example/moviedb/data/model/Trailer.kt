package com.example.moviedb.data.model

import android.os.Parcelable
import com.example.moviedb.utils.Constant
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Trailer(
    val id: String?,
    val key: String?,
    val name: String?
) : Parcelable {
    fun getFullTrailerPreviewImagePath() =
        if (key.isNullOrBlank()) null else Constant.BASE_URL_IMAGE + key + Constant.BASE_URL_IMAGE_DEFAULT
}

@Parcelize
data class ListTrailer(
    @Json(name = "results")
    val listTrailer: List<Trailer>
) : Parcelable
