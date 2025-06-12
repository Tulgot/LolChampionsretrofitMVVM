package com.tulgot.lol.presentation.championdetailscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tulgot.lol.domain.LolChampionsRepository
import com.tulgot.lol.domain.network.UiStates
import com.tulgot.lol.domain.room.RoomManager
import com.tulgot.lol.modules.firestore.domain.FireStoreManager
import com.tulgot.lol.presentation.ChampionDetailsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChampionDetailsViewModel @Inject constructor(
    private val lolChampionsRepository: LolChampionsRepository,
    private val roomManager: RoomManager,
    private val fireStoreManager: FireStoreManager,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _championDetailsState = MutableStateFlow(ChampionDetailsState())
    val championDetailsState = _championDetailsState.asStateFlow()
    val user = Firebase.auth.currentUser

    var checkDB = mutableStateOf(true)

    init {
        val args = savedStateHandle.toRoute<ChampionDetailsRoute>().name
        loadChampionDetails(args)

    }

    private fun getFavoriteChampionsFireStore() {
        viewModelScope.launch(Dispatchers.IO) {
            val championlist = fireStoreManager.getFavoriteByUser(user?.uid.toString())
            Log.i("TAG", "getFavoriteChampionsFireStore: ${championlist.first()}")
        }
    }

    fun championRoom() {
        insertDB()
        checkDB.value = false
        addChampionDetailFireStore()
    }

    private fun insertDB() {
        viewModelScope.launch(Dispatchers.IO) {
            val championDetail = _championDetailsState.value.championDetails?.data?.first()
            try {
                roomManager.insertChampionDetail(championDetail!!)
                roomManager.insertPassive(championDetail.passive!!, championDetail.id.toString())
                roomManager.insertSpell(championDetail.spells!!, championDetail.name.toString())
            } catch (e: Exception) {
                e.stackTraceToString()
            }
        }
    }

    private fun addChampionDetailFireStore() {
        viewModelScope.launch(Dispatchers.IO) {
            val championDetail = _championDetailsState.value.championDetails?.data?.first()
            try {
                fireStoreManager.addFavoriteChampion(championDetail!!, user?.uid.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun deleteChampionDetailFireStore(championId: String) {
        viewModelScope.launch(Dispatchers.IO) {
//            val roomChampionList = roomManager.getAllChampions()
            fireStoreManager.deleteFavoriteChampion(championId, user?.uid.toString())
        }
    }

    fun deleteChampionDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                val championId = _championDetailsState.value.championDetails?.data?.first()?.id.toString()
                roomManager.deleteChampionDetail(championId)
                deleteChampionDetailFireStore(championId)
            }
            checkDB.value = true

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