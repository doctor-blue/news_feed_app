package com.example.newsfeedapp.model.entity

import com.example.newsfeedapp.base.Entity
import com.google.gson.annotations.SerializedName

class PublisherEntity(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("icon")
    var icon: String? = null
) : Entity()