package com.yassir.moviesappyassir.domain.model

data class MovieDetailsModel(
    val budget: Int,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
)