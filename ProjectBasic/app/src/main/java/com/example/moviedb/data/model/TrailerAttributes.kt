package com.example.moviedb.data.model

import android.os.Parcelable
import com.example.moviedb.utils.Constant
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Trailers(
    @Json(name = "results")
    val trailers: List<TrailerAttributes>
) : Parcelable

@Parcelize
data class TrailerAttributes(
    val id: String?,
    val key: String?,
    val name: String?
) : Parcelable {
    fun getFullTrailerPreviewImagePath() = Constant.BASE_URL_IMAGE + key + Constant.BASE_URL_IMAGE_DEFAULT
}
