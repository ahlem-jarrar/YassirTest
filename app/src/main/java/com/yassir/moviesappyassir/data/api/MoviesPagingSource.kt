package com.yassir.moviesappyassir.data.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yassir.moviesappyassir.data.exception.GeneralException
import com.yassir.moviesappyassir.data.mapper.toMovieModel
import com.yassir.moviesappyassir.domain.model.MovieModel
import com.yassir.moviesappyassir.domain.model.MoviesFilter
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

private const val STARTING_PAGE_INDEX = 1

class MoviesPagingSource(private val api: ApiClient, private val filter: MoviesFilter?) :
    PagingSource<Int, MovieModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieModel>) =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPageIndex = state.pages.indexOf(state.closestPageToPosition(anchorPosition))
            state.pages.getOrNull(anchorPageIndex + 1)?.prevKey ?: state.pages.getOrNull(
                anchorPageIndex - 1
            )?.nextKey
        }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: STARTING_PAGE_INDEX
        var mappedList = emptyList<MovieModel>()
        return try {
            val moviesResult = api.fetchMovies(
                page = position
            )
            if (moviesResult.results.isNotEmpty()) {
                mappedList = moviesResult.results.map { it.toMovieModel() }
            }

            LoadResult.Page(
                data = mappedList,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (mappedList.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            if (exception is UnknownHostException)
                LoadResult.Error(UnknownHostException())
            else LoadResult.Error(GeneralException())
        } catch (exception: HttpException) {
            LoadResult.Error(GeneralException())
        }
    }
}