package com.example.moviedb.data.local.db

import androidx.room.TypeConverter
import com.example.moviedb.data.model.Casts
import com.example.moviedb.data.model.Genre
import com.example.moviedb.data.model.Trailers
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type


class Converters {
    var moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromGenreList(data: String): List<Genre>? {
        if (data == null) {
            return null
        }
        val type = Types.newParameterizedType(
            List::class.java, Genre::class.java
        )
        val adapter: JsonAdapter<List<Genre>> =
            moshi.adapter(type)
        return adapter.fromJson(data)
    }

    @TypeConverter
    fun genreListToString(genres: List<Genre>): String {
        val type = Types.newParameterizedType(
            List::class.java, Genre::class.java
        )
        val adapter: JsonAdapter<List<Genre>> =
            moshi.adapter(type)
        return adapter.toJson(genres)
    }

    @TypeConverter
    fun fromCastList(data: String): Casts? {
        if (data == null) {
            return null
        }
        val jsonAdapter: JsonAdapter<Casts> =
            moshi.adapter<Casts>(Casts::class.java)
        return jsonAdapter.fromJson(data)
    }

    @TypeConverter
    fun castListToString(casts: Casts): String {
        val jsonAdapter: JsonAdapter<Casts> =
            moshi.adapter<Casts>(Casts::class.java)
        return jsonAdapter.toJson(casts)
    }

    @TypeConverter
    fun fromTrailerList(data: String): Trailers? {
        if (data == null) {
            return null
        }
        val jsonAdapter: JsonAdapter<Trailers> =
            moshi.adapter<Trailers>(Trailers::class.java)
        return jsonAdapter.fromJson(data)
    }

    @TypeConverter
    fun trailerListToString(trailers: Trailers): String {
        val jsonAdapter: JsonAdapter<Trailers> =
            moshi.adapter<Trailers>(Trailers::class.java)
        return jsonAdapter.toJson(trailers)
    }
}
