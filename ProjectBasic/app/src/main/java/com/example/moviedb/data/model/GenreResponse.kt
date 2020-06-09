package com.example.moviedb.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class GenreResponse(
    val genres: List<Genre>? = null
) : Parcelable
