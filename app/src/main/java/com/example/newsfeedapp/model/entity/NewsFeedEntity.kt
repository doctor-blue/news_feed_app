package com.example.newsfeedapp.model.entity

import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.newsfeedapp.base.Entity
import com.example.newsfeedapp.utils.timeFormat
import com.google.gson.annotations.SerializedName

class NewsFeedEntity(
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
    var publisherEntity: PublisherEntity? = null,
    @SerializedName("origin_url")
    var originUrl: String? = null,
    @SerializedName("avatar")
    var avatarEntity: AvatarEntity? = null,
    @SerializedName("images")
    var imagesEntity: List<ImageEntity>? = null,
    @Ignore
    @SerializedName("content")
    var content: ContentEntity? = null
) : Entity() {
    init {
        primaryKey = documentId + title
    }

    fun formatPublishedDate() = publishedDate?.timeFormat() ?: ""
}


