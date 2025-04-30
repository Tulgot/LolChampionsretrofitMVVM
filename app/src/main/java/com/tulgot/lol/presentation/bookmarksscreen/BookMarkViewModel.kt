package com.tulgot.lol.presentation.bookmarksscreen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tulgot.lol.domain.room.model.ChampionRoom
import com.tulgot.lol.domain.room.RoomManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val roomManager: RoomManager
) : ViewModel() {

    init {
        getChamionList()
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
}