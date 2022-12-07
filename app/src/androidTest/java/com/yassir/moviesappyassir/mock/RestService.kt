package com.yassir.moviesappyassir.mock

import com.yassir.moviesappyassir.data.api.ApiClient
import org.mockito.Mockito

class RestService {
    val restService: ApiClient = Mockito.mock(ApiClient::class.java)
}