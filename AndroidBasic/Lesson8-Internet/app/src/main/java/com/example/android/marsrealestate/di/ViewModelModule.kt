package com.example.android.marsrealestate.di

import com.example.android.marsrealestate.screen.overview.OverviewViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel { OverviewViewModel(get()) }
}
