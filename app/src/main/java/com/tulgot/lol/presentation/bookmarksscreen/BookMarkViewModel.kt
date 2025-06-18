package com.tulgot.lol.presentation.bookmarksscreen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tulgot.lol.domain.model.Champion
import com.tulgot.lol.domain.model.Image
import com.tulgot.lol.domain.model.Passive
import com.tulgot.lol.domain.model.Spell
import com.tulgot.lol.domain.network.internetconnectionobserver.data.AndroidConnectivityObserver
import com.tulgot.lol.domain.room.RoomManager
import com.tulgot.lol.domain.room.model.ChampionRoom
import com.tulgot.lol.modules.firestore.domain.FireStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val roomManager: RoomManager,
    private val fireStoreManager: FireStoreManager,
    private val connectivityObserver: AndroidConnectivityObserver
) : ViewModel() {

    private val isConnected = connectivityObserver.isConnected.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        false
    )

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getChamionList()
                if (championList.isEmpty()) {
                    getFavoriteChampionsFireStore()
                }
            }
        }
    }

    var championList = mutableStateListOf<ChampionRoom>()
        private set

    private fun getChamionList() {
        viewModelScope.launch(Dispatchers.IO) {
            roomManager.getAllChampions().let {
                championList.addAll(it)
            }
        }
    }

    private fun getFavoriteChampionsFireStore() {
        viewModelScope.launch(Dispatchers.IO) {
            if (isConnected.value) {
                val user = Firebase.auth.currentUser
                val championList = fireStoreManager.getFavoriteByUser(user?.uid.toString())
                if (championList.championList.isNotEmpty()) {
                    championList.championList.forEach {
                        roomManager.insertChampionDetail(
                            Champion(
                                id = it.id,
                                blurb = it.blurb,
                                image = Image(it.image, ""),
                                name = it.name,
                                lore = it.lore,
                                tags = listOf(it.tags),
                                title = it.title,
                                passive = Passive(
                                    "",
                                    Image("", ""),
                                    ""
                                ),
                                key = "",
                                spells = listOf()
                            )
                        )
                    }
                    championList.passiveList.forEach { passiveRoom ->
                        roomManager.insertPassive(
                            Passive(
                                description = passiveRoom.description,
                                image = Image(passiveRoom.image.toString(), ""),
                                name = passiveRoom.name
                            ),
                            id = passiveRoom.championid.toString()
                        )
                    }

                    val spellList = championList.spellList.map { spellRoom ->
                        Spell(
                            id = spellRoom.id,
                            description = spellRoom.description,
                            image = Image(
                                full = "",
                                group = ""
                            ),
                            name = spellRoom.name
                        )
                    }

                    spellList.forEachIndexed { index, spell ->
                        roomManager.insertSpell(
                            listOf(spell),
                            championList.spellList[index].championid!!
                        )
                    }
                }
            }
        }
    }

}