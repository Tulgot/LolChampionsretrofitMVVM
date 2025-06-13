package com.tulgot.lol.domain.room.model

import com.google.gson.annotations.SerializedName

data class PassiveRoom(
    @SerializedName("championid") val championid: String?=null,
    @SerializedName("description") val description: String?=null,
    @SerializedName("image") val image: String?=null,
    @SerializedName("name") val name: String?=null,
)
