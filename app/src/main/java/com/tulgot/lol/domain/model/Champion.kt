package com.tulgot.lol.domain.model

import com.google.gson.annotations.SerializedName

data class Champion(
    @SerializedName("blurb")
    val blurb: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("image")
    val image: Image?,
    @SerializedName("key")
    val key: String? = "",
    @SerializedName("lore")
    val lore: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("passive")
    val passive: Passive?,
    @SerializedName("spells")
    val spells: Spell?,
    @SerializedName("tags")
    val tags: List<String> = listOf(),
    @SerializedName("title")
    val title: String? = ""
)
