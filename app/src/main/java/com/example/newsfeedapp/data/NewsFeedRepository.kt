package com.example.newsfeedapp.data

import android.app.Application
import androidx.lifecycle.liveData
import com.example.newsfeedapp.model.entity.NewsFeedsEntity
import com.example.newsfeedapp.remote.NewsService
import com.example.newsfeedapp.utils.NEW_FEED_CACHE_FILE_NAME
import com.example.newsfeedapp.utils.Resource
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import java.io.*
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
            writeNewsFeedToCache(data.data)

        } catch (ex: Exception) {
            emit(Resource.Error(null, ex.message ?: "Error"))

            //If remote load fails, try to get data from cache
            emit(Resource.Loading(null))
            try {
                emit(Resource.Success(readNewsFeedFromCache()))
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

    private fun writeNewsFeedToCache(newsFeedsEntity: NewsFeedsEntity) {
        val jsonStr: String = Gson().toJson(newsFeedsEntity)

        val file = File(application.cacheDir, NEW_FEED_CACHE_FILE_NAME)
        val stream = FileOutputStream(file)

        try {
            stream.write(jsonStr.toByteArray())

        } catch (e: IOException) {
            throw e
        } finally {
            stream.close()
        }
    }

    private fun readNewsFeedFromCache(): NewsFeedsEntity {
        val file = File(application.cacheDir, NEW_FEED_CACHE_FILE_NAME)

        val inputStream = FileInputStream(file)

        val gson = Gson()
        val reader: Reader = InputStreamReader(inputStream)

        return gson.fromJson(reader, NewsFeedsEntity::class.java)
    }

}