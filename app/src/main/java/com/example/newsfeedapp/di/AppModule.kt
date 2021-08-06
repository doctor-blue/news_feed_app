package com.example.newsfeedapp.di

import com.example.newsfeedapp.data.NewsFeedRepository
import com.example.newsfeedapp.remote.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    fun provideNewsService() = NewsService.create()

    @Provides
    fun provideNewsRepository(newsService: NewsService) = NewsFeedRepository(newsService)
}