package com.tulgot.lol.data

import com.tulgot.lol.data.response.ChampionResponseDto
import retrofit2.http.GET

interface LolApi {

    @GET("champion.json")
    suspend fun getChampionList(): ChampionResponseDto

    companion object{
        const val BASE_URL = "https://ddragon.leagueoflegends.com/cdn/15.7.1/data/en_US/"
    }

}