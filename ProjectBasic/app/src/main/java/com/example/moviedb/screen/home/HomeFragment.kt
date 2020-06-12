package com.example.moviedb.screen.home

import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviedb.R
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.HomeFragmentBinding
import com.example.moviedb.screen.base.BaseFragment
import com.example.moviedb.screen.detail.DetailFragment
import com.example.moviedb.utils.EndlessScrollListener
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>(),
    TabLayout.OnTabSelectedListener {
    private val endlessScrollListener = EndlessScrollListener(::onLoadMore)
    private val onClickListener = object : MovieAdapter.OnClickListener {
        override fun onItemClick(movie: Movie) {
            viewModel.displayMovieDetails(movie)
        }
    }
    private val viewModelAdapter = MovieAdapter(onClickListener)
    override val layoutId: Int
        get() = R.layout.home_fragment
    override val viewModel: HomeViewModel by viewModel()

    override fun initComponents(view: HomeFragmentBinding) {
        initListenerViewModel(view)
        registerViewModel()
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {}

    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabSelected(tab: TabLayout.Tab?) {
        viewModel.genresSelected.value =
            tab?.position?.let { viewModel.listGenres.value?.get(index = it)?.genresID }
    }

    private fun initListenerViewModel(view: HomeFragmentBinding) {
        viewModel.listMovies.observe(viewLifecycleOwner, Observer { listMovie ->
            listMovie?.apply {
                viewModelAdapter?.submitList(this.toList())
            }
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
                viewModel.getMovieData(genres.getOrNull(0)?.genresID ?: 0, false)
            }
        })
        movieByKeyTabLayout?.addOnTabSelectedListener(this)
        movieByKeyTabLayout.getTabAt(0)?.select()
        view.recycleMovies.adapter = viewModelAdapter
        view.recycleMovies.layoutManager = GridLayoutManager(context, 2)
        view.recycleMovies.addOnScrollListener(endlessScrollListener)
    }

    private fun registerViewModel() {
        viewModel.genresSelected.observe(viewLifecycleOwner, Observer { genresSelected ->
            viewModel.getMovieData(genresSelected, true)
        })

        viewModel.navigateToSelectedMovie.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                val movieDetail =
                    DetailFragment.newInstance(viewModel.navigateToSelectedMovie.value)
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    ?.replace(R.id.mainFrameLayout, movieDetail)
                    ?.addToBackStack(null)
                    ?.commit()
                viewModel.displayPropertyDetailsComplete()
            }
        })
        viewModel.eventNetworkError.observe(
            viewLifecycleOwner,
            Observer<Boolean> { isNetworkError ->
                if (isNetworkError) onNetworkError()
            })
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            showMessage("Network Error")
            viewModel.onNetworkErrorShown()
        }
    }

    private fun onLoadMore() {
        viewModel.loadMoreData()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
