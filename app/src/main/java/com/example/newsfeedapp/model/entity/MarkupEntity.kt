package com.example.newsfeedapp.model.entity


import com.example.newsfeedapp.base.Entity
import com.google.gson.annotations.SerializedName

data class MarkupEntity(
    @SerializedName("markup_type")
    var markupType: Int?,
    @SerializedName("start")
    var start: Int?,
    @SerializedName("end")
    var end: Int?,
    @SerializedName("href")
    var href: String?
): Entity()