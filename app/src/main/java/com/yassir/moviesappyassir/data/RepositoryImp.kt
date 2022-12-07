package com.yassir.moviesappyassir.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yassir.moviesappyassir.data.api.ApiClient
import com.yassir.moviesappyassir.data.api.MoviesPagingSource
import com.yassir.moviesappyassir.data.mapper.toMovieDetailsModel
import com.yassir.moviesappyassir.domain.Repository
import com.yassir.moviesappyassir.domain.model.MovieDetailsModel
import com.yassir.moviesappyassir.domain.model.MovieModel
import com.yassir.moviesappyassir.domain.model.MoviesFilter
import com.yassir.moviesappyassir.domain.utils.Result
import com.yassir.moviesappyassir.domain.utils.executeToResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImp @Inject constructor(
    private val apiClient: ApiClient
) : Repository {

    override fun getTrendingMovies(
        moviesFilter: MoviesFilter?
    ): Flow<PagingData<MovieModel>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = {
                MoviesPagingSource(apiClient, moviesFilter)
            }
        ).flow
    }

    override suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsModel> {
        return executeToResult {
            apiClient.fetchMovieDetails(movieId = movieId).toMovieDetailsModel()
        }
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}