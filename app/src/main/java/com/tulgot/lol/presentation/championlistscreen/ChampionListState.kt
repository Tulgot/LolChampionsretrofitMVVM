package com.tulgot.lol.presentation.championlistscreen

import com.tulgot.lol.domain.model.ChampionResponse
import com.tulgot.lol.domain.network.UiStates

data class ChampionListState(
    val state: UiStates = UiStates.NONE,
    val championList: ChampionResponse? = null
)
