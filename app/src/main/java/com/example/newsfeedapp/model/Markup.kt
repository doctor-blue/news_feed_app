package com.example.newsfeedapp.model


import com.example.newsfeedapp.base.Model
import com.google.gson.annotations.SerializedName

data class Markup(
    @SerializedName("markup_type")
    var markupType: Int?,
    @SerializedName("start")
    var start: Int?,
    @SerializedName("end")
    var end: Int?,
    @SerializedName("href")
    var href: String?
): Model()