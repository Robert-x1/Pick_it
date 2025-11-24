package com.hitech.pickit.auth.di

import com.google.firebase.auth.FirebaseAuth
import com.hitech.pickit.auth.data.repository.AuthRepositoryImpl
import com.hitech.pickit.auth.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth
    ): AuthRepository {
        return AuthRepositoryImpl(auth)
    }
}