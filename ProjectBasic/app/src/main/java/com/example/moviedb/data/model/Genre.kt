package com.example.moviedb.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "genres")
data class Genre(
    @PrimaryKey(autoGenerate = false)
    @Json(name = "id")
    val genresID: Int,
    @Json(name = "name")
    val genresName: String
) : Parcelable
