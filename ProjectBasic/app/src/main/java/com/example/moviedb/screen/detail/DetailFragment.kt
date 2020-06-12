package com.example.moviedb.screen.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import com.example.moviedb.R
import com.example.moviedb.data.model.Genre
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.TrailerAttributes
import com.example.moviedb.databinding.DetailFragmentBinding
import com.example.moviedb.screen.MainActivity
import com.example.moviedb.screen.base.BaseFragment
import com.example.moviedb.screen.detail.adapter.CastAdapter
import com.example.moviedb.screen.detail.adapter.TrailerAdapter
import com.example.moviedb.utils.Constant
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.toolbar_base.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment() : BaseFragment<DetailFragmentBinding, DetailViewModel>() {

    override val viewModel: DetailViewModel by viewModel()

    private val onClickListener = object : TrailerAdapter.OnClickListener {
        override fun onItemClick(trailer: TrailerAttributes) {
            trailer.key?.let { watchYoutubeVideo(it) }
        }
    }
    private val viewModelCastAdapter by lazy { CastAdapter() }
    private val viewModelTrailerAdapter by lazy { TrailerAdapter(onClickListener) }

    override val layoutId: Int
        get() = R.layout.detail_fragment

    override fun initComponents(view: DetailFragmentBinding) {
        movie?.let { viewModel.getDataMovieDetail(it) }
        initToolBar()
        registerViewModel(view)
    }

    private fun registerViewModel(view: DetailFragmentBinding) {
        viewModel.movieDetail.observe(viewLifecycleOwner, Observer { movieDetail ->
            movieDetail.apply {
                viewModelCastAdapter.submitList(movieDetail.credits?.casts)
                viewModelTrailerAdapter.submitList(movieDetail.videos?.trailers)
                movieDetail.genres?.let { initChipView(it) }
            }
        })
        viewModel.eventNetworkError.observe(
            viewLifecycleOwner,
            Observer<Boolean> { isNetworkError ->
                if (isNetworkError) onNetworkError()
            })
        view.castsRecyclerView.adapter = viewModelCastAdapter
        view.moviesTrailerRecyclerView.adapter = viewModelTrailerAdapter
    }

    private fun onNetworkError() {
        viewModel.isNetworkErrorShown.value?.let { isNetworkErrorShown ->
            if (!isNetworkErrorShown) {
                showMessage(getString(R.string.networkError))
                viewModel.onNetworkErrorShown()
            }
        }
    }

    private fun initToolBar() {
        toolbarBase?.let {
            (activity as? MainActivity)?.run {
                setSupportActionBar(it)
                supportActionBar?.run {
                    setDisplayShowTitleEnabled(true)
                    title = arguments?.getString(Constant.BASE_TITLE)
                }
            }
            it.setNavigationOnClickListener {
                activity?.run { supportFragmentManager.popBackStack() }
            }
        }
    }

    private fun initChipView(genres: List<Genre>) {
        for (item in genres) {
            val genresChip = LayoutInflater.from(activity)
                .inflate(R.layout.item_chip, genresChipGroup, false) as Chip
            genresChip.text = item.genresName
            genresChipGroup.addView(genresChip)
        }
    }

    private fun watchYoutubeVideo(id: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Constant.ACTION_YOUTUBE + id))
        val webIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(Constant.BASE_YOUTUBE + id)
        )
        try {
            context?.startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            context?.startActivity(webIntent)
        }
    }

    companion object {
        private const val MOVIE = "MOVIE"
        private var movie: Movie? = null

        fun newInstance(movieArg: Movie?) = DetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MOVIE, movieArg)
                movie = movieArg
            }
        }
    }
}
