package com.example.newsfeedapp.ui.newsfeed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.newsfeedapp.data.NewsFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsFeedViewModel @Inject constructor(private val newsFeedRepository: NewsFeedRepository) :
    ViewModel() {

    private val loadTrigger = MutableLiveData(Unit)

    fun refresh() {
        loadTrigger.value = Unit
    }

    val newsFeedLiveData = loadTrigger.switchMap {
        getNewsFeed()
    }

   private fun getNewsFeed() = newsFeedRepository.getNewsFeed()


}