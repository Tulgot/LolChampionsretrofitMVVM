package com.tulgot.lol.data.response


import com.google.gson.annotations.SerializedName


data class ImageDto(
    @SerializedName("full")
    val full: String? = "",
    @SerializedName("group")
    val group: String? = ""
)