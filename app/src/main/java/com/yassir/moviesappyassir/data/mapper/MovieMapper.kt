package com.yassir.moviesappyassir.data.mapper

import com.yassir.moviesappyassir.data.model.ApiMovie
import com.yassir.moviesappyassir.domain.model.MovieModel

internal fun ApiMovie.toMovieModel() =
    MovieModel(
        id = id ?: 0,
        title = title ?: "",
        overview = overview ?: "",
        releaseDate = releaseDate ?: "",
        posterPath = posterPath ?: "",
    )