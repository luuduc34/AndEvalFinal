package com.technifutur.andeval5.service

import com.technifutur.andeval5.model.MovieResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MovieServiceImpl {
    private val baseUrl = "https://api.themoviedb.org/3/"

    fun getRetrofit(): Retrofit {
        val okBuilder = OkHttpClient().newBuilder().apply {
            connectTimeout(15, TimeUnit.SECONDS)
            callTimeout(15, TimeUnit.SECONDS)
            readTimeout(15, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
        }
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okBuilder.build())
            .build()
    }

    suspend fun getMovies(movie: String, apiKey: String): Response<MovieResponse> = getRetrofit().create(MovieService::class.java).getAllMovies(movie,apiKey)

}
