package com.example.moviedb.screen.favorite

import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.moviedb.R
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.FavoriteFragmentBinding
import com.example.moviedb.screen.base.BaseFragment
import com.example.moviedb.screen.base.OnItemClickListener
import com.example.moviedb.screen.detail.DetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteFragment : BaseFragment<FavoriteFragmentBinding, FavoriteViewModel>() {

    private val onItemClickListener = object : OnItemClickListener<Movie> {
        override fun onItemClick(model: Movie) {
            val movieDetail =
                DetailFragment.newInstance(model)
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                add(R.id.mainFrameLayout, movieDetail)
                addToBackStack(null)
                commit()
            }
        }
    }

    private val onItemDeleteClickListener = object : FavoriteAdapter.OnItemDeleteClickListener {
        override fun onItemDelete(movie: Movie) {
            viewModel.deleteMovie(movie)
        }
    }

    private val viewModelAdapter = FavoriteAdapter(onItemClickListener, onItemDeleteClickListener)

    override val layoutId: Int
        get() = R.layout.favorite_fragment
    override val viewModel: FavoriteViewModel by viewModel()

    override fun initComponents(view: FavoriteFragmentBinding) {
        initListenerViewModel(view)
        registerViewModel()
    }

    private fun initListenerViewModel(view: FavoriteFragmentBinding) {
        viewModel.listMovies.observe(viewLifecycleOwner, Observer { listMovie ->
            listMovie?.apply {
                viewModelAdapter.submitList(this.toList())
            }
        })
        view.favoriteMovieRecyclerView.adapter = viewModelAdapter
    }

    private fun registerViewModel() {
        viewModel.eventDatabaseError.observe(
            viewLifecycleOwner,
            Observer<Boolean> { isDatabaseError ->
                if (isDatabaseError) onDatabaseError()
            })
    }

    private fun onDatabaseError() {
        viewModel.isDatabaseErrorShown.value?.let { isNetworkErrorShown ->
            if (!isNetworkErrorShown) {
                showMessage(getString(R.string.databaseError))
                viewModel.onDatabaseErrorShown()
            }
        }
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}
