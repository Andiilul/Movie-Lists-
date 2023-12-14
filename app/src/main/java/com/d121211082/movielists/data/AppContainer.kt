package com.d121211082.movielists.data

import com.d121211082.movielists.data.repository.MoviesRepository
import com.d121211082.movielists.data.source.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppCointainer {
    val moviesRepository: MoviesRepository
}
class DefaultAppContainer: AppCointainer {

    private val BASE_URL = "https://api.themoviedb.org"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val moviesRepository: MoviesRepository
        get() = MoviesRepository(retrofitService)
}