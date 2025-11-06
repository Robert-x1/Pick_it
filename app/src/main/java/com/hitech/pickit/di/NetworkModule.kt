package com.hitech.pickit.di

import com.hitech.pickit.movie.data.remote.tmdpApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header(
                        "Authorization",
                        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwMmMzYWRmYjNhOTUzNWNjY2NkMDRhNTRkMmE1ZjQ3OSIsIm5iZiI6MTc1NTc4ODE2Ni4yNDYsInN1YiI6IjY4YTczMzg2YTQ0OTI1NjA0NzNlOGFhZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.utzvRqvLKJ5gagwsXlCJgkrWEMjdg5ozXTCJNhfDxz4"
                    )
                    .header("Content-Type", "application/json")
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
        }

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideTmdpApi(retrofit: Retrofit): tmdpApi {
        return retrofit.create(tmdpApi::class.java)
    }
}