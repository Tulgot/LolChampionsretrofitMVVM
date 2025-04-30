package com.tulgot.lol.domain.room.model

import com.google.gson.annotations.SerializedName

data class ChampionRoom(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("blurb")
    val blurb: String? = "",
    @SerializedName("image")
    val image: String? = "",
    @SerializedName("key")
    val key: String? = "",
    @SerializedName("lore")
    val lore: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("passive")
    val passive: String = "",
    @SerializedName("spells")
    val spells: String = "",
    @SerializedName("tags")
    val tags: String = "",
    @SerializedName("title")
    val title: String? = ""
)