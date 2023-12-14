package com.d121211082.movielists.data.source.remote


import com.d121211082.movielists.data.response.GetMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5NGM1NjgxYWE3MTdjODlmZTdmMmE1ZTcyYTNhNWZmZCIsInN1YiI6IjYyZTRhOTJiZmM1ZjA2MDA1OWMyOGUwOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.fhTRa5WuoK85MU20AmtsDA7PAZNfBR4DrRs6a31a9Rc"
    )
    @GET("3/discover/movie")
    suspend fun getMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 2,): GetMoviesResponse
}