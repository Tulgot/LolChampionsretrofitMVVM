package com.tulgot.lol.domain.model

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("full")
    val full: String? = "",
    @SerializedName("group")
    val group: String? = ""
)
