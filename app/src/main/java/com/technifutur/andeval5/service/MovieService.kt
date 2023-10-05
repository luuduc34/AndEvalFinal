package com.technifutur.andeval5.service

import com.technifutur.andeval5.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("search/movie")
    suspend fun getAllMovies(
        @Query("query") movie: String,
        @Query("api_key") apiKey: String
    ): Response<MovieResponse>
}