package com.example.newsfeedapp.model.entity


import com.example.newsfeedapp.base.Entity
import com.google.gson.annotations.SerializedName

data class DetailNewsFeedEntity(
    @SerializedName("document_id")
    var documentId: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("published_date")
    var publishedDate: String?,
    @SerializedName("origin_url")
    var originUrl: String?,
    @SerializedName("publisher")
    var publisher: PublisherEntity?,
    @SerializedName("template_type")
    var templateType: String?,
    @SerializedName("sections")
    var sectionEntities: List<SectionEntity>?
): Entity()