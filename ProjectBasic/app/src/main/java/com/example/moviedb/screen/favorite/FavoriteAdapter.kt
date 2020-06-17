package com.example.moviedb.screen.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.ItemFavoriteBinding
import com.example.moviedb.screen.base.OnItemClickListener

class FavoriteAdapter(
    private val onItemClickListener: OnItemClickListener<Movie>,
    private val onItemDeleteClickListener: FavoriteAdapter.OnItemDeleteClickListener
) :
    ListAdapter<Movie, FavoriteAdapter.FavoriteViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriteViewHolder(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val movie = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(movie)
        }
        holder.bindData(movie, onItemDeleteClickListener)
    }

    class FavoriteViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(
            movie: Movie,
            onItemDeleteClickListener: OnItemDeleteClickListener
        ) {
            binding.executePendingBindings()
            binding.apply {
                this.movie = movie
                deleteImageView.setOnClickListener {
                    onItemDeleteClickListener.onItemDelete(movie)
                }
                executePendingBindings()
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }

    interface OnItemDeleteClickListener {
        fun onItemDelete(movie: Movie)
    }
}
