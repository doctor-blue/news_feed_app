package com.example.newsfeedapp.data

import android.app.Application
import androidx.lifecycle.liveData
import com.example.newsfeedapp.remote.NewsService
import com.example.newsfeedapp.utils.Cache.readNewsFeedFromCache
import com.example.newsfeedapp.utils.Cache.writeNewsFeedToCache
import com.example.newsfeedapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class NewsFeedRepository @Inject constructor(
    private val newsService: NewsService,
    private val application: Application
) {

    fun getNewsFeed() = liveData(Dispatchers.IO) {
        emit(Resource.Loading(null))
        try {
            val data = Resource.Success(newsService.getNewsFeeds())

            emit(data)
            // after successfully fetching remote data, save to cache file
            writeNewsFeedToCache(data.data, application)

        } catch (ex: Exception) {
            emit(Resource.Error(null, ex.message ?: "Error"))

            //If remote load fails, try to get data from cache
            emit(Resource.Loading(null))
            try {
                emit(Resource.Success(readNewsFeedFromCache(application)))
            } catch (e: Exception) {
                emit(Resource.Error(null, ex.message ?: "Error"))
            }
        }
    }


    fun getNewsFeedDetail() = liveData(Dispatchers.IO) {
        emit(Resource.Loading(null))
        try {
            emit(Resource.Success(newsService.getDetailFeed()))
        } catch (ex: Exception) {
            emit(Resource.Error(null, ex.message ?: "Error"))
        }
    }


}