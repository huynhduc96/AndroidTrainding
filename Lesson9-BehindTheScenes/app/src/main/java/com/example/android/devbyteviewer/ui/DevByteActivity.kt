/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.devbyteviewer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.example.android.devbyteviewer.R
import com.example.android.devbyteviewer.work.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * This is a single activity application that uses the Navigation library. Content is displayed
 * by Fragments.
 */
class DevByteActivity : AppCompatActivity() {
  private val applicationScope = CoroutineScope(Dispatchers.Default)
  val constraints = Constraints.Builder()
      .setRequiredNetworkType(NetworkType.UNMETERED)
      .build()

  /**
   * Called when the activity is starting.  This is where most initialization
   * should go
   */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    delayedInit()
    setContentView(R.layout.activity_dev_byte_viewer)
  }

  private fun delayedInit() {
    applicationScope.launch {
      Timber.plant(Timber.DebugTree())
      setupRecurringWork()
    }
  }

  /**
   * Setup WorkManager background job to 'fetch' new network data daily.
   */
  private fun setupRecurringWork() {
    val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
        .setConstraints(constraints)
        .build()
    WorkManager.getInstance().enqueueUniquePeriodicWork(
        RefreshDataWorker.WORK_NAME,
        ExistingPeriodicWorkPolicy.KEEP,
        repeatingRequest
    )
  }
}
