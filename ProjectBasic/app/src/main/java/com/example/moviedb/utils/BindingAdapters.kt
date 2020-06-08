package com.example.moviedb.utils

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.moviedb.R
import com.example.moviedb.data.model.Genre
import com.example.moviedb.screen.home.HomeViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.home_fragment.*

@BindingAdapter("loandingApiStatus")
fun bindStatus(
    statusImageView: ImageView,
    status: HomeViewModel.LoadingApiStatus?
) {
    when (status) {
        HomeViewModel.LoadingApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        HomeViewModel.LoadingApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        HomeViewModel.LoadingApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("android:onRefresh")
fun SwipeRefreshLayout.setSwipeRefreshLayoutOnRefreshListener(viewModel: HomeViewModel) {
    setOnRefreshListener {
        viewModel.refreshData()
        isRefreshing = false
    }
}
