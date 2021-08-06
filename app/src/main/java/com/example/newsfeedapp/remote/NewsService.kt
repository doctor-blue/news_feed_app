package com.example.newsfeedapp.remote

import com.example.newsfeedapp.model.entity.DetailNewsFeedEntity
import com.example.newsfeedapp.model.entity.NewsFeedsEntity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsService {

    @GET("/Akaizz/static/master/newsfeed.json")
    suspend fun getNewsFeeds(): NewsFeedsEntity

    @GET("/Akaizz/static/master/detail.json")
    suspend fun getDetailFeed(): DetailNewsFeedEntity

    companion object {
        private const val BASE_URL = "https://raw.githubusercontent.com/"

        fun create(): NewsService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(NewsService::class.java)
    }
}