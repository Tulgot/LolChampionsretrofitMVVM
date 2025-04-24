package com.tulgot.lol.data.database

import com.tulgot.lol.data.database.entities.ChampionEntity
import com.tulgot.lol.domain.model.Champion
import com.tulgot.lol.domain.room.ChampionRoom


fun Champion.toChampionEntity() =
    ChampionEntity(
        id = id.toString(),
        blurb = blurb.toString(),
        image = image.toString(),
        name = name.toString(),
        key = key.toString(),
        lore = lore.toString(),
        passive = passive.toString(),
        spells = spells.toString(),
        tags = tags.toString(),
        title = title.toString()
    )

fun ChampionEntity.toChampionRoom() =
    ChampionRoom(
        id = id,
        blurb = blurb,
        image = image,
        name = name,
        key = key,
        lore = lore,
        passive = passive,
        spells = spells,
        tags = tags,
        title = title
    )

//val numbers = listOf(1, 2, 3, 4, 5, 6)
//val snumbers = numbers.toString()
//val lnumbers = "[1, 2, 3, 4, 5, 6]"
//val ynumbers = lnumbers.substring(1, lnumbers.length-1)
//val xnumbers = ynumbers.split(",").map { it.trim() }
//val numberlist = xnumbers.toList()
//println(numberlist[2])