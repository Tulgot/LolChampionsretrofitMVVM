package com.tulgot.lol.presentation.championdetailscreen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.tulgot.lol.domain.SPLASH_ASSET
import com.tulgot.lol.domain.SQUARE_ASSET
import com.tulgot.lol.domain.model.Champion
import com.tulgot.lol.domain.network.UiStates

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChampionDetailsScreen(
    name: String,
    championDetailsViewModel: ChampionDetailsViewModel = hiltViewModel()
) {

    val championDetailsResult by championDetailsViewModel.championDetailsState.collectAsState()
    val details = championDetailsResult.championDetails?.data?.firstOrNull()


    when (championDetailsResult.state) {
        UiStates.FAILURE -> {
            Toast.makeText(
                LocalContext.current,
                "No hay datos de: ${details?.name}",
                Toast.LENGTH_SHORT
            ).show()
        }

        UiStates.LOADING -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(80.dp),
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }

        UiStates.NONE -> {}
        UiStates.SUCCESS -> {
            Scaffold(modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        colors = topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        title = {
                            Text(details?.name.toString())
                        }
                    )

                }) { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    ChampionDetalCard(details)

                }

            }
        }
    }

}

@Composable
fun ChampionDetalCard(details: Champion?) {
    Row(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = SPLASH_ASSET + "${details?.id}_0.jpg",
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Name: "+details?.name.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,

            )

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
            AsyncImage(
                model = SQUARE_ASSET + "${details?.id}.png",
                contentDescription = null,

                modifier = Modifier
                    .size(40.dp)
                    .fillMaxWidth(),
            )
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
    Text("Title: "+details?.title.toString())
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = "Classes:")

    LazyColumn() {
        items(details?.tags!!.size) { index ->
            Text(details.tags[index])
        }
    }


}