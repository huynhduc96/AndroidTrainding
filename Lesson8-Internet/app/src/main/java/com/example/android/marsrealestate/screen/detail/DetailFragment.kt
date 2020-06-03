/*
 *  Copyright 2018, The Android Open Source Project
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.android.marsrealestate.screen.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.android.marsrealestate.databinding.FragmentDetailBinding

/**
 * This [Fragment] will show the detailed information about a selected piece of Mars real estate.
 */
class DetailFragment : Fragment() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {

    val application = requireNotNull(activity).application
    val binding = FragmentDetailBinding.inflate(inflater)
    val marsProperty = DetailFragmentArgs.fromBundle(requireArguments()).selectedProperty
    val viewModelFactory = DetailViewModelFactory(marsProperty, application)
    binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)
    binding.setLifecycleOwner(this)
    return binding.root
  }
}
