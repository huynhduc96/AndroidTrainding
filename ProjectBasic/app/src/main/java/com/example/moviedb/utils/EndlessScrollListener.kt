package com.example.moviedb.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class EndlessScrollListener(private val loadMore: () -> Unit) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is LinearLayoutManager
            && layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 1
        ) {
            loadMore()
        } else if (layoutManager is GridLayoutManager
            && layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 1
        ) {
            val index = layoutManager.findLastVisibleItemPosition()
            val count = layoutManager.itemCount - 1
            loadMore()
        } else if (layoutManager is StaggeredGridLayoutManager
            && layoutManager.findLastVisibleItemPositions(
                IntArray(layoutManager.spanCount)
            )[0] == layoutManager.itemCount - 1
        ) {
            loadMore()
        }
    }
}
