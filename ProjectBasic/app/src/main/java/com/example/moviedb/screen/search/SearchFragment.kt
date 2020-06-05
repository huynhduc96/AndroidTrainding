package com.example.moviedb.screen.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviedb.R
import com.example.moviedb.databinding.SearchFragmentBinding
import com.example.moviedb.screen.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment<SearchFragmentBinding, SearchViewModel>() {
    override val layoutId: Int
        get() = R.layout.search_fragment
    override val viewModel: SearchViewModel by viewModel()

    override fun initComponents(view: SearchFragmentBinding) {
    }
}
