package com.tulgot.lol.presentation

import kotlinx.serialization.Serializable


@Serializable
object ChampionList

@Serializable
data class ChampionDetails(val name: String)

@Serializable
object Settings