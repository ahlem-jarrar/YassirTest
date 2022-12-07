package com.yassir.moviesappyassir.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.yassir.moviesappyassir.databinding.ItemMovieBinding
import com.yassir.moviesappyassir.ui.model.ViewMovieItem

class MoviesViewHolder internal constructor(private val binding: ItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: ViewMovieItem) {
        binding.movie = movie
    }
}