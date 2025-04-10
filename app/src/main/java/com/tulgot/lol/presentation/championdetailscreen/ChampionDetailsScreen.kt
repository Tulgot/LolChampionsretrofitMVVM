package com.tulgot.lol.presentation.championdetailscreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tulgot.lol.domain.network.UiStates

@Composable
fun ChampionDetailsScreen(
    championDetailsViewModel: ChampionDetailsViewModel = hiltViewModel()
) {

    /*val champDetailsResult by championDetailsViewModel.championDetailsState.collectAsState()
    val champitonDetails = champDetailsResult.championDetails?.data?.toList()


    when(champDetailsResult.state){
        UiStates.FAILURE -> {
            //Toast.makeText(LocalContext.current, "no hay datos", Toast.LENGTH_SHORT).show()
            Log.i("champion_detail", "Falla")
        }
        UiStates.LOADING -> {
            Log.i("champion_detail", "cargando")
        }
        UiStates.NONE -> {}
        UiStates.SUCCESS -> {
            Log.i("champion_detail", "ChampionDetailsScreen: ${champDetailsResult.championDetails.toString()}")
        }
    }*/

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(fontSize = 25.sp, text = "hola")


    }
}