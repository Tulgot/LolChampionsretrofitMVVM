package com.tulgot.lol.domain.model

import com.google.gson.annotations.SerializedName

data class ChampionResponse(
    @SerializedName("data")
    val data: List<Champion>?,
    @SerializedName("format")
    val format: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("version")
    val version: String? = ""
)
