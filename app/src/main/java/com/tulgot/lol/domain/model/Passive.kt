package com.tulgot.lol.domain.model

import com.google.gson.annotations.SerializedName
import com.tulgot.lol.data.response.ImageDto

data class Passive(
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("image")
    val image: Image?,
    @SerializedName("name")
    val name: String? = ""
)