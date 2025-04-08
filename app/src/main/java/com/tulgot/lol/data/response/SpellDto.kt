package com.tulgot.lol.data.response


import com.google.gson.annotations.SerializedName


data class SpellDto(
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("image")
    val image: ImageDto? = ImageDto(),
    @SerializedName("name")
    val name: String? = ""
)