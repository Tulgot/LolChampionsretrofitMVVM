package com.tulgot.lol.presentation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object ChampionList

@Serializable
data class ChampionDetails(val name: String)

@Serializable
object Settings

@Serializable
object BookMarks

@Serializable
data class BookMarksDetail(val id: String)