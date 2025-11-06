package com.hitech.pickit.movie.data.remote

import com.hitech.pickit.movie.data.networking.dto.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Query


interface tmdpApi {
    @GET("movie/popular")
    suspend fun discoverMovie(
        @Query("page") page: Int
    ): MovieResponseDto
}