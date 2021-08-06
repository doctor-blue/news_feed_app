package com.example.newsfeedapp.di

import android.app.Application
import com.example.newsfeedapp.NewsFeedApplication
import com.example.newsfeedapp.data.NewsFeedRepository
import com.example.newsfeedapp.remote.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsService() = NewsService.create()

    @Provides
    @Singleton
    fun provideNewsRepository(newsService: NewsService, application: Application) =
        NewsFeedRepository(newsService, application)
}