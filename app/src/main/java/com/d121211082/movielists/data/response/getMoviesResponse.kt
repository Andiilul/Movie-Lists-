package com.d121211082.movielists.data.response

import com.d121211082.movielists.data.models.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMoviesResponse(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<Movie>,
    @SerialName("total_pages")
    val total_pages: Int,
    @SerialName("total_results")
    val total_results: Int
)