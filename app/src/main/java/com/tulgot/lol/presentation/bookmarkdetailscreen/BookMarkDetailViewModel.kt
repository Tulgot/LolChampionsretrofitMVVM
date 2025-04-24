package com.tulgot.lol.presentation.bookmarkdetailscreen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.tulgot.lol.domain.room.ChampionRoom
import com.tulgot.lol.domain.room.RoomManager
import com.tulgot.lol.presentation.BookMarksDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkDetailViewModel @Inject constructor(
    private val roomManager: RoomManager,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    init {
        val args = savedStateHandle.toRoute<BookMarksDetail>().id
        getRoomChampionByID(args)
    }

    var championDetail = mutableStateListOf<ChampionRoom>()
        private set

    var taglist = mutableStateListOf<String>()
        private set

    private fun getRoomChampionByID(args: String){
        viewModelScope.launch(Dispatchers.IO) {
            roomManager.getChampionById(args).let {
                championDetail.addAll(it)
                stringToList(it.first().tags)
                stringToSpellList(it.first().spells)
                stringToPassive(it.first().passive)

            }
        }
    }

    private fun stringToList(tags: String){
        val stringmodification = tags.substring(1, tags.length-1)
        val splitstring = stringmodification.split(",").map { it.trim() }
        val listofstrings = splitstring.toList()
        taglist.addAll(listofstrings)
    }

    private fun stringToSpellList(spell: String){
        Log.i("TAG", "stringToSpellList: ${spell}")
    }

    private fun stringToPassive(passive: String){
        Log.i("TAG", "stringToPassive: ${passive}")
    }

}