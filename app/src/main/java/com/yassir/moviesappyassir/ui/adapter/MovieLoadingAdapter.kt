package com.yassir.moviesappyassir.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yassir.moviesappyassir.databinding.ItemMovieBinding

class MovieLoadingAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<MovieLoadingAdapter.LoadingStateViewHolder>() {

    class LoadingStateViewHolder(binding: ItemMovieBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            /*   if (loadState is LoadState.Error) {
                   tvErrorMessage.text = loadState.error.localizedMessage
               }
               progressBar.isVisible = loadState is LoadState.Loading
               tvErrorMessage.isVisible = loadState !is LoadState.Loading
               btnRetry.isVisible = loadState !is LoadState.Loading
   */
        }

    }

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadingStateViewHolder(binding, retry)
    }
}