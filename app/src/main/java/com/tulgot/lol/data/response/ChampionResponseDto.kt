package com.tulgot.lol.data.response


import com.google.gson.annotations.SerializedName


data class ChampionResponseDto(
    @SerializedName("data")
    val champion: Map<String, ChampionDto>? = emptyMap(),
    @SerializedName("format")
    val format: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("version")
    val version: String? = ""
)

fun Map<String, ChampionDto>.toChampionList(): List<ChampionDto> = this.values.toList()

