package com.yassir.moviesappyassir.data.mapper

import com.yassir.moviesappyassir.data.api.ApiClient.Companion.BASE_IMAGES_URL
import com.yassir.moviesappyassir.data.model.MovieDetailsResponse
import com.yassir.moviesappyassir.domain.model.MovieDetailsModel

fun MovieDetailsResponse.toMovieDetailsModel(): MovieDetailsModel {
    return MovieDetailsModel(
        budget ?: 0,
        id ?: 0,
        originalLanguage ?: "",
        originalTitle ?: "",
        overview ?: "",
        popularity ?: 0.0,
        if (posterPath != null) {
            "$BASE_IMAGES_URL$posterPath"
        } else "",
        releaseDate ?: "",
        title ?: "",
    )
}