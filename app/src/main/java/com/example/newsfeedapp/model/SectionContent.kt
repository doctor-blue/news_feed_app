package com.example.newsfeedapp.model

import com.example.newsfeedapp.base.Model
import com.google.gson.annotations.SerializedName

data class SectionContent(
    @SerializedName("href")
    val href: String?,
    @SerializedName("caption")
    val caption: String?,
    @SerializedName("duration")
    val duration: Int?,
    @SerializedName("preview_image")
    val previewImage: Image?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("markups")
    val markups: List<Markup>?,
    @SerializedName("main_color")
    val mainColor: String?,
    @SerializedName("original_width")
    val originalWidth: Int?,
    @SerializedName("original_height")
    val originalHeight: Int?
) : Model()