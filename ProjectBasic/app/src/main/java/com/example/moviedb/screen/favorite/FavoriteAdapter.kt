package com.example.moviedb.screen.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.ItemFavoriteBinding

class FavoriteAdapter(
    private val onItemClickListener: FavoriteAdapter.OnItemClickListener,
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
        holder.bind(movie, onItemDeleteClickListener)
    }

    class FavoriteViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            movie: Movie,
            onItemDeleteClickListener: FavoriteAdapter.OnItemDeleteClickListener
        ) {
            binding.movie = movie
            binding.deleteImageView.setOnClickListener {
                onItemDeleteClickListener.onItemDelete(movie)
            }
            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }

    interface OnItemDeleteClickListener {
        fun onItemDelete(movie: Movie)
    }

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
