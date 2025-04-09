package com.tulgot.lol.presentation.championlistscreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.tulgot.lol.domain.IMAGEURL
import com.tulgot.lol.domain.model.Champion
import com.tulgot.lol.domain.network.UiStates

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChampionListScreen(
    championListViewModel: ChampionListViewModel = hiltViewModel()
) {

    val championListResult by championListViewModel.championList.collectAsState()
    val championList = championListResult.championList?.data?.toList()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Champions")
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp)
        ) {
            when (championListResult.state) {
                UiStates.FAILURE -> {
                    Toast.makeText(LocalContext.current, "No hay datos", Toast.LENGTH_SHORT).show()
                }

                UiStates.LOADING -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.CenterHorizontally),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                UiStates.SUCCESS -> {LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    championList?.let {
                        items(it.size) {
                            ChampionCard(championList[it])
                        }
                    }

                    }
                }

                UiStates.NONE -> {}
            }

        }
    }


}

@Composable
fun ChampionCard(championList: Champion) {

    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        AsyncImage(
            model = IMAGEURL + "${championList.id}_0.jpg",
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .weight(0.3f)
                .height(180.dp)
        )
        Column(modifier = Modifier
            .weight(0.7f)
            .padding(vertical = 10.dp)) {
            Text(
                championList.id.toString(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = championList.blurb.toString(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 24.sp
            )
        }


    }
}