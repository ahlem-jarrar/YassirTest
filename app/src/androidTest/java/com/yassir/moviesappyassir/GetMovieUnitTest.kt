package com.yassir.moviesappyassir

import com.yassir.moviesappyassir.data.RepositoryImp
import com.yassir.moviesappyassir.data.api.ApiClient
import com.yassir.moviesappyassir.domain.Repository
import com.yassir.moviesappyassir.mock.RestService
import com.yassir.moviesappyassir.utils.CoroutineTestRule
import com.yassir.moviesappyassir.utils.CreateMovieWIthTitle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class GetMovieUnitTest {
    private lateinit var repository: Repository
    private lateinit var api: ApiClient

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setup() = runTest {
        api = RestService().restService

        // Get movie id=2 from the rest server
        Mockito.`when`(api.fetchMovieDetails(movieId = 2))
            .thenReturn(CreateMovieWIthTitle.createMovie())

        repository = RepositoryImp(api)

    }


}