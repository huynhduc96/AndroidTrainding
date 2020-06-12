package com.example.moviedb.screen.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.data.model.CastAttributes
import com.example.moviedb.databinding.ItemCastBinding

class CastAdapter : ListAdapter<CastAttributes, CastAdapter.CastViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder =
        CastViewHolder(ItemCastBinding.inflate(LayoutInflater.from(parent.context)))


    override fun onBindViewHolder(holder: CastViewHolder, position: Int) =
        holder.bind(getItem(position))

    class CastViewHolder(private val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cast: CastAttributes) {
            binding.cast = cast
            binding.executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CastAttributes>() {
        override fun areItemsTheSame(oldItem: CastAttributes, newItem: CastAttributes): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CastAttributes, newItem: CastAttributes): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
