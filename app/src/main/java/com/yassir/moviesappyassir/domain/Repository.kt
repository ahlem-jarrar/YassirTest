package com.yassir.moviesappyassir.domain

import androidx.paging.PagingData
import com.yassir.moviesappyassir.domain.model.MovieDetailsModel
import com.yassir.moviesappyassir.domain.model.MovieModel
import com.yassir.moviesappyassir.domain.model.MoviesFilter
import com.yassir.moviesappyassir.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getTrendingMovies(moviesFilter: MoviesFilter?): Flow<PagingData<MovieModel>>
    suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsModel>
}