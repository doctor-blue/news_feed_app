package com.example.newsfeedapp.model

import com.example.newsfeedapp.base.Model
import com.google.gson.annotations.SerializedName

data class Section(
    @SerializedName("section_type")
    var sectionType: Int? = null,
    @SerializedName("content")
    var sectionContent: SectionContent? = null
): Model()