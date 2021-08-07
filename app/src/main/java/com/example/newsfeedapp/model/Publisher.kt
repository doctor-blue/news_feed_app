package com.example.newsfeedapp.model

import com.example.newsfeedapp.base.Model
import com.google.gson.annotations.SerializedName

class Publisher(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("icon")
    var icon: String? = null
) : Model()