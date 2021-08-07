package com.example.newsfeedapp.remote

import com.example.newsfeedapp.model.DetailNewsFeed
import com.example.newsfeedapp.model.NewsFeeds
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsService {

    @GET("/Akaizz/static/master/newsfeed.json")
    suspend fun getNewsFeeds(): NewsFeeds

    @GET("/Akaizz/static/master/detail.json")
    suspend fun getDetailFeed(): DetailNewsFeed

    companion object {
        private const val BASE_URL = "https://raw.githubusercontent.com/"

        fun create(): NewsService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(NewsService::class.java)
    }
}