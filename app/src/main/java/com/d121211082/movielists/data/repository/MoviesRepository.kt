package com.d121211082.movielists.data.repository

import com.d121211082.movielists.data.response.GetMoviesResponse
import com.d121211082.movielists.data.source.remote.ApiService

class MoviesRepository(private val apiService: ApiService) {

    suspend fun getMovies(): GetMoviesResponse {
        return apiService.getMovies()
    }
}