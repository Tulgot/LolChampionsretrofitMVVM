package com.tulgot.lol.domain.room

import com.google.gson.annotations.SerializedName

data class PassiveRoom(
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("image")
    val image: String? = ""
)
