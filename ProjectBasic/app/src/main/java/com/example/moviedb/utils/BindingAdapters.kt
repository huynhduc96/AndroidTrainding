package com.example.moviedb.utils

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviedb.R
import com.example.moviedb.screen.home.HomeViewModel

@BindingAdapter("loadingApiStatus")
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

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}
