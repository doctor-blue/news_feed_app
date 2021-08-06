package com.example.newsfeedapp.model.entity


import com.example.newsfeedapp.base.Entity
import com.google.gson.annotations.SerializedName

class AvatarEntity(
    @SerializedName("href")
    var href: String? = null,
    @SerializedName("main_color")
    var mainColor: String? = null,
    @SerializedName("width")
    var width: Int? = null,
    @SerializedName("height")
    var height: Int? = null
): Entity()