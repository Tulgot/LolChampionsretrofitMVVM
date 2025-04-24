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

fun Champion.toChampionRoom() = ChampionRoom(
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