package com.yassir.moviesappyassir.ui.mapper

import com.yassir.moviesappyassir.data.api.ApiClient.Companion.BASE_IMAGES_URL
import com.yassir.moviesappyassir.domain.model.MovieModel
import com.yassir.moviesappyassir.ui.model.ViewMovieItem

fun MovieModel.toViewMovieItem(onClick: () -> Unit) = ViewMovieItem(
    id = id,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    imageUrl = "$BASE_IMAGES_URL$posterPath",
    onClick = onClick,
)