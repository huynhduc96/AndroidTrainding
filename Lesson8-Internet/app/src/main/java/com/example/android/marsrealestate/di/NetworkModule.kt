package com.example.android.marsrealestate.di

import com.example.android.marsrealestate.data.service.MarsApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://mars.udacity.com/"
val networkModule = module {

  single {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    retrofit(moshi)
  }
  single {
    get<Retrofit>().create(MarsApiService::class.java)
  }
}


fun retrofit(moshi: Moshi) = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()
