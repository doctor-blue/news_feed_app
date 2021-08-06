package com.example.newsfeedapp.model.entity

import com.example.newsfeedapp.base.Entity
import com.google.gson.annotations.SerializedName

data class SectionEntity(
    @SerializedName("section_type")
    var sectionType: Int? = null,
    @SerializedName("content")
    var sectionContentEntity: SectionContentEntity? = null
): Entity()