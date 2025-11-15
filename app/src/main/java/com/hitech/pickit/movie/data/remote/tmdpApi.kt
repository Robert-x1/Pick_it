package com.hitech.pickit.movie.data.remote

import com.hitech.pickit.movie.data.networking.dto.MovieResponse
import com.hitech.pickit.movie.data.networking.dto.NetworkTMDbWrapper
import retrofit2.http.GET
import retrofit2.http.Query


interface tmdpApi {
    @GET("movie/popular")
    suspend fun discoverMovie(
        @Query("page") page: Int
    ): NetworkTMDbWrapper<MovieResponse>
}