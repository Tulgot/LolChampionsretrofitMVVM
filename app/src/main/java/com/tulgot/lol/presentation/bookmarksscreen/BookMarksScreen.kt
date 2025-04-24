package com.tulgot.lol.presentation.bookmarksscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tulgot.lol.domain.room.ChampionRoom

@Composable
fun BookMarksScreen(
    bookMarkViewModel: BookMarkViewModel = hiltViewModel()
) {

    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "aqui esta el dato: ${bookMarkViewModel.championList}",
            modifier = Modifier.padding(top = 60.dp))
    }
}