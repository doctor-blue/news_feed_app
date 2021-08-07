package com.example.newsfeedapp.model

import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.newsfeedapp.base.Model
import com.example.newsfeedapp.utils.timeFormat
import com.google.gson.annotations.SerializedName

class NewsFeedModel(
    @PrimaryKey
    var primaryKey: String? = null,
    @SerializedName("document_id")
    var documentId: String = "",
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("content_type")
    var contentType: String? = null,
    @SerializedName("published_date")
    var publishedDate: String? = null,
    @SerializedName("publisher")
    var publisher: Publisher? = null,
    @SerializedName("origin_url")
    var originUrl: String? = null,
    @SerializedName("avatar")
    var avatar: Avatar? = null,
    @SerializedName("images")
    var images: List<Image>? = null,
    @Ignore
    @SerializedName("content")
    var content: Content? = null
) : Model() {
    init {
        primaryKey = documentId + title
    }

    fun formatPublishedDate() = publishedDate?.timeFormat() ?: ""
}


