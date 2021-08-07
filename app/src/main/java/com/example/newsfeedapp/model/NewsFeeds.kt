package com.example.newsfeedapp.model

import com.example.newsfeedapp.base.Model
import com.google.gson.annotations.SerializedName

class NewsFeeds(
    @SerializedName("items")
    var itemsEntity: List<NewsFeedModel>?

) : Model()