package com.yassir.moviesappyassir.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yassir.moviesappyassir.databinding.ItemMovieBinding
import com.yassir.moviesappyassir.ui.model.ViewMovieItem


class MoviesListAdapter : PagingDataAdapter<ViewMovieItem, MoviesViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let { item -> holder.bind(item) }
    }


    object COMPARATOR : DiffUtil.ItemCallback<ViewMovieItem>() {
        override fun areItemsTheSame(oldItem: ViewMovieItem, newItem: ViewMovieItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ViewMovieItem,
            newItem: ViewMovieItem
        ): Boolean =
            oldItem == newItem
    }


}
