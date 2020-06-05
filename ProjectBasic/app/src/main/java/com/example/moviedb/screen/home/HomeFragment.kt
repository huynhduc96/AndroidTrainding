package com.example.moviedb.screen.home

import com.example.moviedb.R
import com.example.moviedb.databinding.HomeFragmentBinding
import com.example.moviedb.screen.base.BaseFragment
import com.example.moviedb.screen.favorite.FavoriteFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    override val layoutId: Int
        get() = R.layout.home_fragment
    override val viewModel: HomeViewModel by viewModel()

    override fun initComponents(view: HomeFragmentBinding) {
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}
