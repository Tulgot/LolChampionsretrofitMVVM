package com.tulgot.lol.data.response


import com.google.gson.annotations.SerializedName


data class PassiveDto(
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("image")
    val image: ImageDto? = ImageDto(),
    @SerializedName("name")
    val name: String? = ""
)