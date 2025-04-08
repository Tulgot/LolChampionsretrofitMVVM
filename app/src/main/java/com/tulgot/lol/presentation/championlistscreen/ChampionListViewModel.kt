package com.tulgot.lol.presentation.championlistscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tulgot.lol.domain.LolChampionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChampionListViewModel @Inject constructor(
    private val lolChampionsRepository: LolChampionsRepository
): ViewModel() {

    private val _championList = MutableStateFlow(ChampionListState())
    val championList = _championList.asStateFlow()

    init {
        viewModelScope.launch {
                loadChampionList()
            }

    }

    private fun loadChampionList(){
        viewModelScope.launch {
            lolChampionsRepository.getAllChampions().collect(){Response->
                _championList.update {
                    it.copy(championList = Response)
                }
            }
        }
    }

}