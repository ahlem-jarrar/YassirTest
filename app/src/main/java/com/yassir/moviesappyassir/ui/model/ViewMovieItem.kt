package com.yassir.moviesappyassir.ui.model

data class ViewMovieItem(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val imageUrl: String,
    val onClick: () -> Unit,
) {
    fun onClick() = onClick.invoke()
}