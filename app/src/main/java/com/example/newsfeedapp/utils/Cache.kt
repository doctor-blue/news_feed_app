package com.example.newsfeedapp.utils

import android.app.Application
import com.example.newsfeedapp.model.entity.NewsFeedsEntity
import com.google.gson.Gson
import java.io.*

object Cache {
    fun writeNewsFeedToCache(newsFeedsEntity: NewsFeedsEntity, application: Application) {
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

    fun readNewsFeedFromCache(application: Application): NewsFeedsEntity {
        val file = File(application.cacheDir, NEW_FEED_CACHE_FILE_NAME)

        val inputStream = FileInputStream(file)

        val gson = Gson()
        val reader: Reader = InputStreamReader(inputStream)

        return gson.fromJson(reader, NewsFeedsEntity::class.java)
    }
}