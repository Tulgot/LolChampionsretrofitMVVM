package com.tulgot.lol.presentation.bookmarksscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun BookMarksScreen() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

        Text(text = "BookMarks", textAlign = TextAlign.Center)

    }
}