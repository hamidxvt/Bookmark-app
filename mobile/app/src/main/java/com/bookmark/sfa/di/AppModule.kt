package com.bookmark.sfa.di

import com.bookmark.sfa.data.api.ApiClient
import com.bookmark.sfa.data.api.ApiService
import com.bookmark.sfa.data.local.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(apiClient: ApiClient): ApiService = apiClient.service
}
