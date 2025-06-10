package com.tulgot.lol.presentation.championdetailscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.tulgot.lol.domain.LolChampionsRepository
import com.tulgot.lol.domain.network.UiStates
import com.tulgot.lol.domain.room.RoomManager
import com.tulgot.lol.presentation.ChampionDetailsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChampionDetailsViewModel @Inject constructor(
    private val lolChampionsRepository: LolChampionsRepository,
    private val roomManager: RoomManager,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _championDetailsState = MutableStateFlow(ChampionDetailsState())
    val championDetailsState = _championDetailsState.asStateFlow()

    var checkDB = mutableStateOf(true)

    init {

        val args = savedStateHandle.toRoute<ChampionDetailsRoute>().name
        loadChampionDetails(args)

    }

    fun championRoom() {
        insertDB()
    }

    private fun insertDB() {
        viewModelScope.launch(Dispatchers.IO) {
            val championDetail = _championDetailsState.value.championDetails?.data?.first()
            try {
                roomManager.insertChampionDetail(championDetail!!)
                roomManager.insertPassive(championDetail.passive!!, championDetail.name.toString())
                roomManager.insertSpell(championDetail.spells!!, championDetail.name.toString())
            } catch (e: Exception) {
                e.stackTraceToString()
            }
        }
    }

    private fun getRoomChampionById(args: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (roomManager.getChampionById(args).isNotEmpty()) {
                checkDB.value = false
            }
        }
    }

    private fun loadChampionDetails(name: String) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _championDetailsState.update {
                    it.copy(state = UiStates.LOADING)
                }
            } catch (e: Exception) {
                e.stackTraceToString()
            }
            lolChampionsRepository.getChampionDetails(name).catch { cause ->
                Log.e(this::class.simpleName, cause.toString())
                _championDetailsState.update {
                    it.copy(
                        championDetails = null,
                        state = UiStates.FAILURE
                    )
                }
            }.collect { response ->
                _championDetailsState.update {
                    it.copy(
                        championDetails = response,
                        state = UiStates.SUCCESS
                    )
                }
                getRoomChampionById(name)
            }

        }
    }
}