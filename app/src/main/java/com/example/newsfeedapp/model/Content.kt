package com.example.newsfeedapp.model

import com.google.gson.annotations.SerializedName

class Content(
    @SerializedName("href")
    var href: String? = null,
    @SerializedName("preview_image")
    var previewImage: Image? = null,
    @SerializedName("duration")
    var duration: Int = 0,
)