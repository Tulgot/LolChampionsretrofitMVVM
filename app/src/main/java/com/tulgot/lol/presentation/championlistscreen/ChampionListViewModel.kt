package com.tulgot.lol.presentation.championlistscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tulgot.lol.domain.LolChampionsRepository
import com.tulgot.lol.domain.network.UiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChampionListViewModel @Inject constructor(
    private val lolChampionsRepository: LolChampionsRepository,
) : ViewModel() {

    private var _championListState = MutableStateFlow(ChampionListState())
    val championListState = _championListState.asStateFlow()

    init {
        viewModelScope.launch {
            loadChampionList()
        }
    }

    private fun loadChampionList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _championListState.update {
                    it.copy(state = UiStates.LOADING)
                }
            } catch (e: Exception) {
                e.stackTraceToString()
            }
            lolChampionsRepository.getAllChampions().catch { cause ->
                Log.e(this::class.simpleName, cause.toString())
                _championListState.update {
                    it.copy(
                        championList = null,
                        state = UiStates.FAILURE
                    )
                }
            }.collect { response ->
                _championListState.update {
                    it.copy(
                        championList = response,
                        state = UiStates.SUCCESS
                    )
                }

            }
        }
    }

}