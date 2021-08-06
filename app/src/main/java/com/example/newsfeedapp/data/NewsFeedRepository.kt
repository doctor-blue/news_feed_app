package com.example.newsfeedapp.data

import androidx.lifecycle.liveData
import com.example.newsfeedapp.remote.NewsService
import com.example.newsfeedapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsFeedRepository @Inject constructor(private val newsService: NewsService) {

     fun getNewsFeed() = liveData(Dispatchers.IO) {
        emit(Resource.Loading(null))
        try {
            emit(Resource.Success(newsService.getNewsFeeds()))
        } catch (ex: IOException) {
            emit(Resource.Error(null, ex.message ?: "Error"))
        } catch (ex: HttpException) {
            emit(Resource.Error(null, ex.message ?: "Error"))
        }
    }

}