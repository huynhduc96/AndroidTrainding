package com.example.android.marsrealestate.di

import android.content.Context
import androidx.room.Room
import com.example.android.marsrealestate.data.local.dao.ImageDao
import com.example.android.marsrealestate.data.local.db.ImagesDatabase
import com.example.android.marsrealestate.data.repository.ImagesRepository
import com.example.android.marsrealestate.data.repository.impl.ImagesRepositoryImpl
import com.example.android.marsrealestate.data.service.MarsApiService
import org.koin.dsl.module

val repositoryModule = module {
  //Database
  single { createImagesRoomDatabase(get()) }
  single { createImageDao(get()) }
  single { createImageRepositoryImpl(get(), get()) }

}

private fun createImagesRoomDatabase(context: Context) =
    Room.databaseBuilder(context, ImagesDatabase::class.java, "image_database").build()

fun createImageDao(imageDatabase: ImagesDatabase) = imageDatabase.ImageDao

fun createImageRepositoryImpl(imageDao: ImageDao, marsApiService: MarsApiService) = ImagesRepositoryImpl(imageDao, marsApiService)
