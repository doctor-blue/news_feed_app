package com.example.newsfeedapp.model


import com.example.newsfeedapp.base.Model
import com.google.gson.annotations.SerializedName

class Avatar(
    @SerializedName("href")
    var href: String? = null,
    @SerializedName("main_color")
    var mainColor: String? = null,
    @SerializedName("width")
    var width: Int? = null,
    @SerializedName("height")
    var height: Int? = null
): Model()