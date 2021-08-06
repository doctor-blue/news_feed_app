package com.example.newsfeedapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.newsfeedapp.data.NewsFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsFeedRepository: NewsFeedRepository
):ViewModel() {

    private val loadTrigger = MutableLiveData(Unit)

    fun refresh() {
        loadTrigger.value = Unit
    }

    val detailFeedLiveData = loadTrigger.switchMap {
        getDetailFeed()
    }

     fun getDetailFeed() = newsFeedRepository.getNewsFeedDetail()
}