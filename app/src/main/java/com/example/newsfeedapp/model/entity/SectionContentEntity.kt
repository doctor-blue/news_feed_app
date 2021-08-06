package com.example.newsfeedapp.model.entity

import com.example.newsfeedapp.base.Entity
import com.google.gson.annotations.SerializedName

data class SectionContentEntity(
    @SerializedName("href")
    val href: String?,
    @SerializedName("caption")
    val caption: String?,
    @SerializedName("duration")
    val duration: Int?,
    @SerializedName("preview_image")
    val previewImageEntity: ImageEntity?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("markups")
    val markupEntities: List<MarkupEntity>?,
    @SerializedName("main_color")
    val mainColor: String?,
    @SerializedName("original_width")
    val originalWidth: Int?,
    @SerializedName("original_height")
    val originalHeight: Int?
) : Entity()