package com.tulgot.lol.presentation.championlistscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tulgot.lol.di.IoDispatcher
import com.tulgot.lol.domain.LolChampionsRepository
import com.tulgot.lol.domain.network.UiStates
import com.tulgot.lol.domain.network.internetconnectionobserver.domain.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChampionListViewModel @Inject constructor(
    private val lolChampionsRepository: LolChampionsRepository,
    connectivityObserver: ConnectivityObserver,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private var _championListState = MutableStateFlow(ChampionListState())
    val championListState = _championListState.asStateFlow()
    val isConnected = connectivityObserver.isConnected.stateIn(viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        false )

    init {
        viewModelScope.launch(dispatcher){
            loadChampionList()
        }
    }

    fun loadChampionList() {
        viewModelScope.launch(dispatcher){
            _championListState.update {
                    it.copy(state = UiStates.LOADING)
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