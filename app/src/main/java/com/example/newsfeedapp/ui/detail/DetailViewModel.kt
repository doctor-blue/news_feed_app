package com.example.newsfeedapp.ui.detail

import androidx.lifecycle.ViewModel
import com.example.newsfeedapp.data.NewsFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsFeedRepository: NewsFeedRepository
) : ViewModel() {
    fun getDetailFeed() = newsFeedRepository.getNewsFeedDetail()
}