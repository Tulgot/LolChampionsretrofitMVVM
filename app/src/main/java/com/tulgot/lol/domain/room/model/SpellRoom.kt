package com.tulgot.lol.domain.room.model

import com.google.gson.annotations.SerializedName

data class SpellRoom(
    @SerializedName("championid")
    val championid: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("name")
    val name: String? = "",
)
