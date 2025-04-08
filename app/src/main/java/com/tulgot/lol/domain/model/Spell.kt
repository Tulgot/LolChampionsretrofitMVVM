package com.tulgot.lol.domain.model

import com.google.gson.annotations.SerializedName
import com.tulgot.lol.data.response.ImageDto

data class Spell (
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("image")
    val image: ImageDto? = ImageDto(),
    @SerializedName("name")
    val name: String? = ""
)