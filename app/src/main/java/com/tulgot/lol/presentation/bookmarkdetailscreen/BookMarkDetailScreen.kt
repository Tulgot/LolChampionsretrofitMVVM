package com.tulgot.lol.presentation.bookmarkdetailscreen

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.rounded.NotInterested
import androidx.compose.material3.Button
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.tulgot.lol.domain.PASSIVE_ASSET
import com.tulgot.lol.domain.SPELL_ASSET
import com.tulgot.lol.domain.SPLASH_ASSET
import com.tulgot.lol.domain.SQUARE_ASSET
import com.tulgot.lol.domain.room.model.PassiveRoom
import com.tulgot.lol.domain.room.model.SpellRoom
import okhttp3.internal.wait

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookMarkDetailScreen(
    navigateToBookMarksRoute: () -> Unit,
    bookMarkDetailViewModel: BookMarkDetailViewModel = hiltViewModel(),
) {

    val isConnected = bookMarkDetailViewModel.isConnected.collectAsState()

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {uri -> bookMarkDetailViewModel.storeImage(uri)}
    )

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(bookMarkDetailViewModel.championDetail.first().name.toString())
                }
            )
        },
        floatingActionButton = {

            if (isConnected.value){
                FloatingActionButton(
                    onClick = {
                        bookMarkDetailViewModel.deleteChampionDetail()
                        navigateToBookMarksRoute()
                    },

                    ) { Icon(Icons.Rounded.NotInterested, null) }
            }

        },
        floatingActionButtonPosition = FabPosition.End,

    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts
                                .PickVisualMedia
                                .ImageOnly)
                        )
                    }) {
                        AsyncImage(
                            model = SPLASH_ASSET + "${bookMarkDetailViewModel.championDetail.first().id}_0.jpg",
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
                            text = bookMarkDetailViewModel.championDetail.first().name.toString(),
                            fontSize = 20.sp,
                        )


                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            AsyncImage(
                                model = SQUARE_ASSET + "${bookMarkDetailViewModel.championDetail.first().id}.png",
                                contentDescription = null,

                                modifier = Modifier
                                    .size(40.dp)
                                    .fillMaxWidth(),
                            )
                        }
                    }

                    Text(
                        text = "Title: ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(
                        text = bookMarkDetailViewModel.championDetail.first().title?.first()
                            ?.uppercase() + bookMarkDetailViewModel.championDetail.first().title?.drop(
                            1
                        ),
                        fontSize = 20.sp
                    )

                    Row {
                        Column {
                            Text(
                                text = "Class(es): ",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }

                        Column {
                            repeat(bookMarkDetailViewModel.taglist.size) {
                                Text(
                                    text = bookMarkDetailViewModel.taglist[it],
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
                                text = bookMarkDetailViewModel.championDetail.first().lore.toString(),
                                fontSize = 20.sp
                            )
                        }
                    }
                }

                Column(modifier = Modifier.padding(8.dp)) {
                    SpellsBookMarked(bookMarkDetailViewModel.spell)
                }
                Column(modifier = Modifier.padding(8.dp)) {
                    PassiveBookMarked(bookMarkDetailViewModel.passive.first())
                }
            }
        }
    }

}

@Composable
fun PassiveBookMarked(passive: PassiveRoom) {
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
                        model = PASSIVE_ASSET + passive.image,
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

@Composable
fun SpellsBookMarked(spell: List<SpellRoom>) {
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
                                    model = SPELL_ASSET + spell[it].id + ".png",
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