package com.example.moviedb.data.local.db

import androidx.room.TypeConverter
import com.example.moviedb.data.model.Casts
import com.example.moviedb.data.model.Genre
import com.example.moviedb.data.model.Trailers
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converters {

    private var moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromGenreList(data: String): List<Genre>? {
        if (!data.isBlank()) {
            val type = Types.newParameterizedType(List::class.java, Genre::class.java)
            val adapter = moshi.adapter<List<Genre>>(type)
            return adapter.fromJson(data)
        }
        return null
    }

    @TypeConverter
    fun genreListToString(genres: List<Genre>): String {
        val type = Types.newParameterizedType(List::class.java, Genre::class.java)
        val adapter = moshi.adapter<List<Genre>>(type)
        return adapter.toJson(genres)
    }

    @TypeConverter
    fun fromCastList(data: String): Casts? {
        if (!data.isBlank()) {
            val jsonAdapter = moshi.adapter(Casts::class.java)
            return jsonAdapter.fromJson(data)
        }
        return null
    }

    @TypeConverter
    fun castListToString(casts: Casts): String {
        val jsonAdapter = moshi.adapter(Casts::class.java)
        return jsonAdapter.toJson(casts)
    }

    @TypeConverter
    fun fromTrailerList(data: String): Trailers? {
        if (!data.isBlank()) {
            val jsonAdapter = moshi.adapter(Trailers::class.java)
            return jsonAdapter.fromJson(data)
        }
        return null
    }

    @TypeConverter
    fun trailerListToString(trailers: Trailers): String {
        val jsonAdapter = moshi.adapter<Trailers>(Trailers::class.java)
        return jsonAdapter.toJson(trailers)
    }
}
