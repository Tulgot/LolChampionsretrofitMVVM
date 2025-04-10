package com.tulgot.lol.presentation.championdetailscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tulgot.lol.domain.LolChampionsRepository
import com.tulgot.lol.domain.network.UiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChampionDetailsViewModel @Inject constructor(
    private val lolChampionsRepository: LolChampionsRepository
): ViewModel(){

    private var _championDteailsState = MutableStateFlow(ChampionDetailsState())
    val championDetailsState = _championDteailsState.asStateFlow()

    init {
        viewModelScope.launch {
            loadChampionDetails()
        }
    }

    private fun loadChampionDetails(){
        viewModelScope.launch {
            try {
                _championDteailsState.update {
                    it.copy(state = UiStates.LOADING)
                }
            }catch (e: Exception){
                e.stackTraceToString()
            }
            lolChampionsRepository.getChampionDetails(championDetailsState.value.name).catch { cause ->
                Log.e(this::class.simpleName, cause.toString())
                _championDteailsState.update {
                    it.copy(
                        championDetails = null,
                        state = UiStates.FAILURE
                    )
                }
            }.collect{ response ->
                _championDteailsState.update {
                    it.copy(
                        championDetails = response,
                        state = UiStates.SUCCESS
                    )
                }
            }

        }
    }

}