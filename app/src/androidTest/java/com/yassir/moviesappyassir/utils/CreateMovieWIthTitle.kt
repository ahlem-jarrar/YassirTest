package com.yassir.moviesappyassir.utils

import com.yassir.moviesappyassir.data.model.MovieDetailsResponse

object CreateMovieWIthTitle {

    fun createMovie() = MovieDetailsResponse(
        adult = false,
        backdropPath = "",
        budget = 10000,
        homepage = "",
        id = 0,
        imdbId = "0",
        originalLanguage = "en",
        originalTitle = "",
        overview = "",
        popularity = 0.0,
        posterPath = "",
        releaseDate = "",
        revenue = 0,
        runtime = 0,
        status = "",
        tagline = "",
        title = "The lord of the ring",
        video = false,
        voteAverage = 2.0,
        voteCount = 0
    )
}