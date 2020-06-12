package com.example.moviedb.data.model

import android.os.Parcelable
import com.example.moviedb.utils.Constant
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cast(
    val cast_id: String?,
    val character: String?,
    val credit_id: String?,
    val gender: Int?,
    val id: String?,
    val name: String?,
    val order: Int?,
    val profile_path: String?
) : Parcelable {

    fun getFullProfilePath() =
        if (profile_path.isNullOrBlank()) null else Constant.SMALL_IMAGE_URL + profile_path
}

@Parcelize
data class ListCast(
    @Json(name = "cast")
    val listCast: List<Cast>
) : Parcelable
