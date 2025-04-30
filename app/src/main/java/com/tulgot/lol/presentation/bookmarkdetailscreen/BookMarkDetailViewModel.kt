package com.tulgot.lol.presentation.bookmarkdetailscreen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.tulgot.lol.domain.room.model.ChampionRoom
import com.tulgot.lol.domain.room.model.PassiveRoom
import com.tulgot.lol.domain.room.RoomManager
import com.tulgot.lol.domain.room.model.SpellRoom
import com.tulgot.lol.presentation.BookMarksDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkDetailViewModel @Inject constructor(
    private val roomManager: RoomManager,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        val args = savedStateHandle.toRoute<BookMarksDetail>().id
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
        val stringmodification = tags.substring(1, tags.length - 1)
        val splitstring = stringmodification.split(",").map { it.trim() }
        val listofstrings = splitstring.toList()
        taglist.addAll(listofstrings)
    }


}