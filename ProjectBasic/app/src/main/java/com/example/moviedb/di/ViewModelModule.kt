package com.example.moviedb.di

import com.example.moviedb.screen.detail.DetailViewModel
import com.example.moviedb.screen.favorite.FavoriteViewModel
import com.example.moviedb.screen.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
