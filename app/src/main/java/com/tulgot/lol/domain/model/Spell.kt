package com.tulgot.lol.domain.model

import com.google.gson.annotations.SerializedName

data class Spell (
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("image")
    val image: Image? = Image(),
    @SerializedName("name")
    val name: String? = ""
)