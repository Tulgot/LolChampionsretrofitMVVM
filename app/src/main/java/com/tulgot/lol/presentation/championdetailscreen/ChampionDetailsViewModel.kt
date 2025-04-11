package com.tulgot.lol.presentation.championdetailscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.tulgot.lol.domain.LolChampionsRepository
import com.tulgot.lol.domain.model.Champion
import com.tulgot.lol.domain.model.ChampionResponse
import com.tulgot.lol.domain.network.UiStates
import com.tulgot.lol.presentation.ChampionDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChampionDetailsViewModel @Inject constructor(
    private val lolChampionsRepository: LolChampionsRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private var _championDteailsState = MutableStateFlow(ChampionDetailsState())
    val championDetailsState = _championDteailsState.asStateFlow()


    init {

        val args = savedStateHandle.toRoute<ChampionDetails>().name

        loadChampionDetails(args)

    }

    private fun loadChampionDetails(name: String){
        viewModelScope.launch {
            try {
                _championDteailsState.update {
                    it.copy(state = UiStates.LOADING)
                }
            } catch (e: Exception) {
                e.stackTraceToString()
            }
            lolChampionsRepository.getChampionDetails(name).catch { cause ->
                Log.e(this::class.simpleName, cause.toString())
                _championDteailsState.update {
                    it.copy(
                        championDetails = null,
                        state = UiStates.FAILURE
                    )
                }
            }.collect { response ->
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
