package com.yassir.moviesappyassir.domain.model

data class MovieModel(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val posterPath: String,
)