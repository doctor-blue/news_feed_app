package com.example.newsfeedapp.model.entity

import com.google.gson.annotations.SerializedName

class ContentEntity(
    @SerializedName("href")
    var href: String? = null,
    @SerializedName("preview_image")
    var previewImage: ImageEntity? = null,
    @SerializedName("duration")
    var duration: Int = 0,
)