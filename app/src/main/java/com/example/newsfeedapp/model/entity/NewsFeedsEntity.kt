package com.example.newsfeedapp.model.entity

import com.example.newsfeedapp.base.Entity
import com.google.gson.annotations.SerializedName

class NewsFeedsEntity(
    @SerializedName("items")
    var itemsEntity: List<NewsFeedEntity>?

) : Entity()