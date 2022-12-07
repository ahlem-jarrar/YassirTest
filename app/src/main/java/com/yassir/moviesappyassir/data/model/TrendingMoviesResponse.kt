package com.yassir.moviesappyassir.data.model

import com.google.gson.annotations.SerializedName

data class TrendingMoviesResponse(
    @SerializedName("page")
    val page  : Int?  = null,
    @SerializedName("results")
    val results : List<ApiMovie> = arrayListOf(),
    @SerializedName("total_pages"   )
    val totalPages   : Int? = null,
    @SerializedName("total_results" )
    val totalResults : Int? = null
)