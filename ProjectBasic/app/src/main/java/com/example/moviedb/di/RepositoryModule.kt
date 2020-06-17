package com.example.moviedb.di

import android.content.Context
import androidx.room.Room
import com.example.moviedb.data.local.dao.FavoritesDao
import com.example.moviedb.data.local.db.AppDatabase
import com.example.moviedb.data.repository.impl.UserRepositoryImpl
import com.example.moviedb.data.service.ApiService
import com.example.moviedb.utils.Constant
import org.koin.dsl.module

val repositoryModule = module {
    single { createAppDatabase(get()) }
    single { createFavoritesDao(get()) }
    single { createUserRepositoryImpl(get(), get()) }
}

private fun createUserRepositoryImpl(apiService: ApiService, favoritesDao: FavoritesDao) =
    UserRepositoryImpl(apiService, favoritesDao)

private fun createAppDatabase(context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, Constant.DATABASE_NAME).build()

private fun createFavoritesDao(appDatabase: AppDatabase) = appDatabase.favoritesDao()
