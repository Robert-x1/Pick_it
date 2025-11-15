package com.hitech.pickit.movie.data.remote

import com.hitech.pickit.movie.data.networking.dto.PersonDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonService {
    @GET("3/person/{personId}")
    suspend fun getPerson(@Path("personId") personId: String): PersonDTO
}
