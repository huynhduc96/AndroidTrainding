package com.example.android.marsrealestate.screen

import android.app.Application
import com.example.android.marsrealestate.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application(){
  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidContext(this@MainApplication)
      modules(appModules)
    }
  }
}
