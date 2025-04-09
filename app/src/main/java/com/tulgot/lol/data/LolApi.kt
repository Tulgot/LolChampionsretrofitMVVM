package com.tulgot.lol.data

import com.tulgot.lol.data.response.ChampionResponseDto
import retrofit2.http.GET

interface LolApi {

    @GET("champion.json")
    suspend fun getChampionList(): ChampionResponseDto

}