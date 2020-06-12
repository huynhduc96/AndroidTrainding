package com.example.moviedb.screen.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.data.model.Trailer
import com.example.moviedb.databinding.ItemTrailerBinding

class TrailerAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Trailer, TrailerAdapter.TrailerViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder =
        TrailerViewHolder(ItemTrailerBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        val trailer = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onItemClick(trailer)
        }
        holder.bind(trailer)
    }

    class TrailerViewHolder(private val binding: ItemTrailerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(trailer: Trailer) {
            binding.trailer = trailer
            binding.executePendingBindings()
        }
    }

    interface OnClickListener {
        fun onItemClick(trailer: Trailer)
    }

    class DiffCallback : DiffUtil.ItemCallback<Trailer>() {
        override fun areItemsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
