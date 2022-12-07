package com.yassir.moviesappyassir.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import coil.load
import com.yassir.moviesappyassir.ui.model.LoadingState


@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(imageUrl: String?) {
    load(imageUrl) {
        crossfade(true)
    }
}


@BindingAdapter("isLoading")
fun ProgressBar.setIsLoading(loadingState: LoadingState?) {
    visibility = if (loadingState == LoadingState.Loading) View.VISIBLE else View.GONE
}
