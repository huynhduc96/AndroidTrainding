package com.example.moviedb.screen.favorite

import com.example.moviedb.R
import com.example.moviedb.databinding.FavoriteFragmentBinding
import com.example.moviedb.screen.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteFragment : BaseFragment<FavoriteFragmentBinding, FavoriteViewModel>() {
    override val layoutId: Int
        get() = R.layout.favorite_fragment
    override val viewModel: FavoriteViewModel by viewModel()

    override fun initComponents(view: FavoriteFragmentBinding) {
    }
}
