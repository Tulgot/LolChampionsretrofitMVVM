package com.tulgot.lol.data

import com.tulgot.lol.data.response.ChampionResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface LolApi {

    @GET("champion.json")
    suspend fun getChampionList(): ChampionResponseDto

    @GET("champion/{name}.json")
    suspend fun getChampionByName(@Path("name") name: String): ChampionResponseDto

}