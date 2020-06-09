package com.example.moviedb.di

import com.example.moviedb.data.repository.impl.UserRepositoryImpl
import com.example.moviedb.data.service.ApiService
import org.koin.dsl.module

val repositoryModule = module {
    single { createUserRepositoryImpl(get()) }
}

private fun createUserRepositoryImpl(apiService: ApiService) = UserRepositoryImpl(apiService)

