package com.example.newsfeedapp.utils

import android.app.Application
import com.example.newsfeedapp.model.NewsFeeds
import com.google.gson.Gson
import java.io.*

object Cache {
    fun writeNewsFeedToCache(newsFeeds: NewsFeeds, application: Application) {
        val jsonStr: String = Gson().toJson(newsFeeds)

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

    fun readNewsFeedFromCache(application: Application): NewsFeeds {
        val file = File(application.cacheDir, NEW_FEED_CACHE_FILE_NAME)

        val inputStream = FileInputStream(file)

        val gson = Gson()
        val reader: Reader = InputStreamReader(inputStream)

        return gson.fromJson(reader, NewsFeeds::class.java)
    }
}