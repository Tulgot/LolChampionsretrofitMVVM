package com.tulgot.lol.domain.room.model

import com.google.gson.annotations.SerializedName

data class PassiveRoom(
    @SerializedName("championid") val championid: String? = "",
    @SerializedName("description") val description: String? = "",
    @SerializedName("image") val image: String? = "",
    @SerializedName("name") val name: String? = "",
)
