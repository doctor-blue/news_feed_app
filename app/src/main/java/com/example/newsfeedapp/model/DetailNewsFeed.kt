package com.example.newsfeedapp.model


import com.example.newsfeedapp.base.Model
import com.google.gson.annotations.SerializedName

data class DetailNewsFeed(
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
    var publisher: Publisher?,
    @SerializedName("template_type")
    var templateType: String?,
    @SerializedName("sections")
    var sections: List<Section>?
): Model()