package com.tulgot.lol.presentation.championdetailscreen

import com.tulgot.lol.domain.model.ChampionResponse
import com.tulgot.lol.domain.network.UiStates

data class ChampionDetailsState(
    val state: UiStates = UiStates.NONE,
    val championDetails: ChampionResponse? = null,
    val name: String = "Aatrox"
)
