package com.example.moviedb.data.model

import android.os.Parcelable
import com.example.moviedb.utils.Constant
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Casts(
    @Json(name = "cast")
    val casts: List<CastAttributes>
) : Parcelable

@Parcelize
data class CastAttributes(
    @Json(name = "cast_id")
    val castId: String?,
    val character: String?,
    @Json(name = "credit_id")
    val creditId: String?,
    val gender: Int?,
    val id: String?,
    val name: String?,
    val order: Int?,
    @Json(name = "profile_path")
    val profilePath: String?
) : Parcelable {

    fun getFullProfilePath() = Constant.SMALL_IMAGE_URL + profilePath
}
