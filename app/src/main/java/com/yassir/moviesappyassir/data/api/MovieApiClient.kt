package com.yassir.moviesappyassir.data.api

import com.yassir.moviesappyassir.data.model.MovieDetailsResponse
import com.yassir.moviesappyassir.data.model.TrendingMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

    companion object {
        private const val API_KEY = "c9856d0cb57c3f14bf75bdc6c063b8f3"
        const val BASE_IMAGES_URL = "https://image.tmdb.org/t/p/w500"
        private const val TRENDING_MOVIES_ENDPOINT: String = "discover/movie"
        private const val MOVIE_DETAILS_ENDPOINT: String = "movie/"
        private const val PARAMS_QUERY_API_KEY: String = "api_key"
        private const val PATH_QUERY_MOVIE_ID: String = "movie_id"
        private const val PARAM_KEY_PAGE: String = "page"
    }

    @GET(value = TRENDING_MOVIES_ENDPOINT)
    suspend fun fetchMovies(
        @Query(value = PARAM_KEY_PAGE) page: Int,
        //TODO PROVIDE THIS THROUGH AN INTERCEPTOR
        @Query(value = PARAMS_QUERY_API_KEY) apiKey: String = API_KEY,
    ): TrendingMoviesResponse


    @GET(value = "$MOVIE_DETAILS_ENDPOINT{$PATH_QUERY_MOVIE_ID}")
    suspend fun fetchMovieDetails(
        @Path(value = PATH_QUERY_MOVIE_ID) movieId: Int,
        @Query(value = PARAMS_QUERY_API_KEY) apiKey: String = API_KEY,
    ): MovieDetailsResponse

}