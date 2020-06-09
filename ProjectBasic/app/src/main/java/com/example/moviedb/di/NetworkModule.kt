package com.example.moviedb.di

import android.content.Context
import com.example.moviedb.BuildConfig
import com.example.moviedb.data.service.ApiService
import com.example.moviedb.data.service.NetworkConnection.NetworkConnectionInterceptor
import com.example.moviedb.di.Constants.CONNECTION_TIMEOUT
import com.example.moviedb.di.Constants.READ_TIMEOUT
import com.example.moviedb.di.Constants.WRITE_TIMEOUT
import com.example.moviedb.utils.Constant
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object Constants {
    const val READ_TIMEOUT: Long = 10
    const val WRITE_TIMEOUT: Long = 10
    const val CONNECTION_TIMEOUT: Long = 10
}

val networkModule = module {

    single {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        retrofit(moshi, get())
    }
    single {
        get<Retrofit>().create(ApiService::class.java)
    }
}


private fun retrofit(moshi: Moshi, context: Context) = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constant.BASE_URL)
    .client(okHttp(context))
    .build()

private fun okHttp(context : Context): OkHttpClient {
    val httpClientBuilder = OkHttpClient.Builder()
    httpClientBuilder.addInterceptor {
        var request = it.request()
        val url = request.url()
            .newBuilder()
            .addQueryParameter(Constant.API_KEY_PAR, BuildConfig.API_KEY)
            .build()
        request = request.newBuilder().url(url).build()
        it.proceed(request)
    }
    httpClientBuilder.addInterceptor(NetworkConnectionInterceptor(context))
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    httpClientBuilder.addInterceptor(logging)
    httpClientBuilder.readTimeout(
        READ_TIMEOUT, TimeUnit.SECONDS
    )
    httpClientBuilder.writeTimeout(
        WRITE_TIMEOUT, TimeUnit.SECONDS
    )
    httpClientBuilder.connectTimeout(
        CONNECTION_TIMEOUT, TimeUnit.SECONDS
    )
    return httpClientBuilder.build()
}
