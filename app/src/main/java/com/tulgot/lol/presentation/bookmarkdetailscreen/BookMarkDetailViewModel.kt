package com.tulgot.lol.presentation.bookmarkdetailscreen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tulgot.lol.domain.network.internetconnectionobserver.domain.ConnectivityObserver
import com.tulgot.lol.domain.room.model.ChampionRoom
import com.tulgot.lol.domain.room.model.PassiveRoom
import com.tulgot.lol.domain.room.RoomManager
import com.tulgot.lol.domain.room.model.SpellRoom
import com.tulgot.lol.modules.firestore.domain.FireStoreManager
import com.tulgot.lol.presentation.BookMarksDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookMarkDetailViewModel @Inject constructor(
    private val roomManager: RoomManager,
    private val fireStoreManager: FireStoreManager,
    private val connectivityObserver: ConnectivityObserver,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val isConnected = connectivityObserver.isConnected.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        false
    )

    init {
        val args = savedStateHandle.toRoute<BookMarksDetailRoute>().id
        getRoomChampionByID(args)
    }

    var championDetail = mutableStateListOf<ChampionRoom>()
        private set

    var taglist = mutableStateListOf<String>()
        private set

    var passive = mutableStateListOf<PassiveRoom>()
        private set

    var spell = mutableStateListOf<SpellRoom>()
        private set

    private val user = Firebase.auth.currentUser

    private fun getRoomChampionByID(args: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (roomManager.getChampionById(args).isNotEmpty()) {
                roomManager.getChampionById(args).let {
                    championDetail.addAll(it)
                    stringToList(it.first().tags)

                }
                roomManager.getPassiveByChampionName(args).let {
                    passive.add(it)
                }
                roomManager.getSpellByChampionName(args).let {
                    spell.addAll(it)
                }
            }
        }
    }

    private fun stringToList(tags: String) {
        val substring  = tags.substring(1, tags.length - 1)
        val substringSplit = substring.split(",").map { it.trim() }
        val listOfSubstring = substringSplit.toList()
        taglist.addAll(listOfSubstring)
    }


    fun deleteChampionDetail(){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.IO){
                roomManager.deleteChampionDetail(championDetail.first().id.toString())
                deleteChampionDetailFireStore(championDetail.first().id.toString())
            }
        }
    }

    private fun deleteChampionDetailFireStore(championId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            fireStoreManager.deleteFavoriteChampion(championId, user?.uid.toString())
        }
    }

}