package com.example.moviedb.screen.home

import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.moviedb.R
import com.example.moviedb.databinding.HomeFragmentBinding
import com.example.moviedb.screen.base.BaseFragment
import com.example.moviedb.screen.favorite.FavoriteFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>(), TabLayout.OnTabSelectedListener {

    override val layoutId: Int
        get() = R.layout.home_fragment
    override val viewModel: HomeViewModel by viewModel()
    private var genresSelected = 0

    override fun initComponents(view: HomeFragmentBinding) {
        viewModel.eventNetworkError.observe(
            viewLifecycleOwner,
            Observer<Boolean> { isNetworkError ->
                if (isNetworkError) onNetworkError()
            })

        viewModel.listGenres.observe(viewLifecycleOwner, Observer { genres ->
            if (movieByKeyTabLayout?.tabCount == 0) {
                activity?.let {
                    movieByKeyTabLayout?.setTabTextColors(
                        ContextCompat.getColor(it, R.color.colorTextLightBlue),
                        ContextCompat.getColor(it, R.color.colorOrange)
                    )
                }
                for (element in genres) {
                    movieByKeyTabLayout.addTab(
                        movieByKeyTabLayout.newTab().setText(element.genresName)
                    )
                }
                genresSelected = genres.getOrNull(0)?.genresID ?: 0
            }
        })
        movieByKeyTabLayout?.addOnTabSelectedListener(this)
        movieByKeyTabLayout.getTabAt(0)?.select()
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            showMessage("Network Error")
            viewModel.onNetworkErrorShown()
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {}

    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabSelected(tab: TabLayout.Tab?) {
        showMessage("selected ${viewModel.listGenres.value?.get(tab?.position!!)?.genresName}")    }
}
