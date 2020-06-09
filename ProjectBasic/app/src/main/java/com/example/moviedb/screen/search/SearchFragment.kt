package com.example.moviedb.screen.search

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
