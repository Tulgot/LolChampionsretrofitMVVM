package com.tulgot.lol.presentation.championdetailscreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.NotInterested
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.tulgot.lol.domain.PASSIVE_ASSET
import com.tulgot.lol.domain.SPELL_ASSET
import com.tulgot.lol.domain.SPLASH_ASSET
import com.tulgot.lol.domain.SQUARE_ASSET
import com.tulgot.lol.domain.model.Champion
import com.tulgot.lol.domain.model.Passive
import com.tulgot.lol.domain.model.Spell
import com.tulgot.lol.domain.network.UiStates


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChampionDetailsScreen(
    championDetailsViewModel: ChampionDetailsViewModel = hiltViewModel(),
) {

    val championDetailsResult by championDetailsViewModel.championDetailsState.collectAsState()
    val details = championDetailsResult.championDetails?.data?.firstOrNull()
    val isConnected = championDetailsViewModel.isConnected.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
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

        },
        floatingActionButton = {
            if (isConnected.value){
                if (championDetailsViewModel.checkDB.value) {
                    FloatingActionButton(
                        onClick = {
                            if (details != null) {
                                championDetailsViewModel.championRoom()
                            }
                        }
                    ) { Icon(Icons.Rounded.FavoriteBorder, null) }
                } else {
                    FloatingActionButton(
                        onClick = {
                            championDetailsViewModel.deleteChampionDetail()
                        }
                    ) {
                        Icon(Icons.Rounded.NotInterested, null)
                    }
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,

        ) { innerPadding ->

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

                Box(
                    modifier = Modifier
                        .padding(top = innerPadding.calculateTopPadding())
                        .fillMaxSize()
                ) {
                    ChampionDetailCard(details)

                }

            }
        }
    }

}


@Composable
fun ChampionDetailCard(details: Champion?) {

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = SPLASH_ASSET + "${details?.id}_0.jpg",
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
        }

        Column(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = "Name: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = details?.name.toString(),
                    fontSize = 20.sp,
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

            Row {
                Text(
                    text = "Title: ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = details?.title?.first()?.uppercase() + details?.title?.drop(1),
                    fontSize = 20.sp
                )
            }

            Row {
                Column {
                    Text(text = "Class(es): ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }

                Column {
                    repeat(details?.tags!!.size) {
                        Text(
                            text = details.tags[it],
                            fontSize = 20.sp
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                Column {
                    Text(text = "Lore: ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
                Column(modifier = Modifier.wrapContentHeight()) {
                    Text(
                        text = details?.lore.toString(),
                        fontSize = 20.sp
                    )
                }
            }
        }

        Column(modifier = Modifier.padding(8.dp)) {
            Spells(details?.spells!!.toList())
        }
        Column(modifier = Modifier.padding(8.dp)) {
            Passive(details?.passive!!)
        }

    }
}

@Composable
fun Spells(spell: List<Spell>) {
    Text(text = "Spells", fontSize = 30.sp, fontWeight = FontWeight.Bold)
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(spell.size) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(200.dp)
            ) {
                Box(modifier = Modifier.clip(RoundedCornerShape(20.dp))) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.primaryContainer else
                                    Color.LightGray
                            )
                            .padding(8.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Row {
                                Text(
                                    text = spell[it].name.toString(),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Row {
                                AsyncImage(
                                    model = SPELL_ASSET + spell[it].image?.full,
                                    contentDescription = null,
                                    contentScale = ContentScale.FillWidth,
                                    modifier = Modifier
                                        .width(100.dp)
                                        .clip(RoundedCornerShape(20.dp))
                                )
                            }

                            Row {
                                Text(
                                    text = spell[it].description.toString(),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}


@Composable
fun Passive(passive: Passive) {
    Text(text = "Passive:", fontSize = 30.sp, fontWeight = FontWeight.Bold)
    Box(modifier = Modifier.clip(RoundedCornerShape(20.dp))) {
        Box(
            modifier = Modifier.background(
                color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.primaryContainer else
                    Color.LightGray
            )
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Row {
                    Text(passive.name.toString(), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Row {
                    AsyncImage(
                        model = PASSIVE_ASSET + passive.image?.full,
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .width(100.dp)
                            .clip(RoundedCornerShape(20.dp))
                    )
                }
                Row {
                    Text(passive.description.toString())
                }
            }
        }
    }
}

