package com.tulgot.lol.presentation.championdetailscreen

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tulgot.lol.domain.PASSIVE_ASSET
import com.tulgot.lol.domain.SPELL_ASSET
import com.tulgot.lol.domain.SQUARE_ASSET
import com.tulgot.lol.domain.model.Champion
import com.tulgot.lol.domain.model.Image
import com.tulgot.lol.domain.model.Passive
import com.tulgot.lol.domain.model.Spell

val championDetails = Champion(
    id = "Aatrox",
    blurb = "Once honored defenders of Shurima against the Void, Aatrox and his brethren would eventually become an even greater threat to Runeterra, and were defeated only by cunning mortal sorcery. But after centuries of imprisonment, Aatrox was the first to find...",
    image = Image(
        full = "Aatrox.png",
        group = "champion"
    ),
    key = "266",
    lore = "Once honored defenders of Shurima against the Void, Aatrox and his brethren would eventually become an even greater threat to Runeterra, and were defeated only by cunning mortal sorcery. But after centuries of imprisonment, Aatrox was the first to find freedom once more, corrupting and transforming those foolish enough to try and wield the magical weapon that contained his essence. Now, with stolen flesh, he walks Runeterra in a brutal approximation of his previous form, seeking an apocalyptic and long overdue vengeance.",
    name = "Aatrox",
    passive = com.tulgot.lol.domain.model.Passive(
        description = "Periodically, Aatrox's next basic attack deals bonus <physicalDamage>physical damage</physicalDamage> and heals him, based on the target's max health",
        image = Image(
            full = "Aatrox_Passive.png",
            group = "passive"
        ),
        name = "Deathbringer Stance"
    ),
    spells = listOf(
        Spell(
            description = "Aatrox slams his greatsword down, dealing physical damage. He can swing three times, each with a different area of effect.",
            id = "AatroxQ",
            image = Image(
                full = "AatroxQ.png",
                group = "spell"
            ),
            name = "The Darkin Blade"
        ),
        Spell(
            description = "Aatrox smashes the ground, dealing damage to the first enemy hit. Champions and large monsters have to leave the impact area quickly or they will be dragged to the center and take the damage again.",
            id = "AatroxW",
            image = Image(
                full = "AatroxW.png",
                group = "spell"
            ),
            name = "Infernal Chains"
        )
    ),
    tags = listOf(
        "Fighter",
        "Mage"
    ),
    title = "The Darkin Blade"
)

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewScreen() {
    val details = championDetails

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Transparent,
                ),
                title = {
                    Row {
                        IconButton(
                            onClick = {},
                        ) {
                            Icon(
                                Icons.Filled.ArrowBackIosNew,
                                null,
                                Modifier.size(15.dp),
                                tint = Color.White
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                }
            ) { Icon(Icons.Rounded.FavoriteBorder, null) }

        },
        floatingActionButtonPosition = FabPosition.End,

        ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        0.0f to Color(74, 74, 109),
                        0.5f to Color(40, 40, 60),
                        0.7f to Color(35, 35, 50),
                        0.9f to Color(30, 30, 45),
                        1f to Color(17, 17, 19),
                        center = Offset(1200f, 200.0f),
                        radius = maxOf(2500, 2500) / 2f
                    )
                )
        ) {
            Box(
                Modifier
                    .padding(top = innerPadding.calculateTopPadding())
                    .padding(horizontal = 20.dp)
            ) {
                ChampionDetailCardPreview(details)
            }
        }

    }
}

@Composable
fun ChampionDetailCardPreview(details: Champion) {

    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
            AsyncImage(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                model = SQUARE_ASSET + "${details.id}.png", //SPLASH_ASSET + "${details.id}_0.jpg",
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(top = 15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                Arrangement.Center
            ) {
                Text(
                    text = details.name.toString(),
                    fontSize = 20.sp,
                    color = Color.White
                )

            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = details.title?.first()?.uppercase() + details.title?.drop(1),
                    fontSize = 20.sp,
                    color = Color.White,
                )
                Column {
                    repeat(details.tags.size) {
                        Text(
                            text = details.tags[it],
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }
                }

            }

            Surface(onClick = {
                expanded = !expanded
            },
                color = Color.Transparent
            ) {
//                if (expanded == false){
//                    Text(
//                        text = details.blurb.toString(),
//                        fontSize = 20.sp,
//                        color = Color.LightGray,
//                    )
//                }else{
//                    Text(
//                        text = details.lore.toString(),
//                        fontSize = 20.sp,
//                        color = Color.LightGray,
//                    )
//                }
               Crossfade(targetState = expanded) { text ->
                   when(text){
                       false -> Text(text = details.blurb.toString(),
                        fontSize = 20.sp,
                        color = Color.LightGray,)
                           true ->Text(
                        text = details.lore.toString(),
                        fontSize = 20.sp,
                        color = Color.LightGray,
                    )
                   }
               }

            }
                



        }

        Column(modifier = Modifier.padding(8.dp)) {
            SpellsPreview(details.spells!!)
        }
        Column(modifier = Modifier.padding(8.dp)) {
            PassivePreview(details.passive!!)
        }

    }
}


@Composable
fun SpellsPreview(spell: List<Spell>) {
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
fun PassivePreview(passive: Passive) {
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