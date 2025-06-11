package com.tulgot.lol.data.database

import com.tulgot.lol.data.database.entities.ChampionEntity
import com.tulgot.lol.data.database.entities.PassiveEntity
import com.tulgot.lol.data.database.entities.SpellEntity
import com.tulgot.lol.domain.model.Champion
import com.tulgot.lol.domain.model.Spell
import com.tulgot.lol.domain.room.model.ChampionRoom
import com.tulgot.lol.domain.room.model.PassiveRoom
import com.tulgot.lol.domain.room.model.SpellRoom


fun Champion.toChampionEntity() =
    ChampionEntity(
        id = id.toString(),
        blurb = blurb.toString(),
        image = image.toString(),
        name = name.toString(),
        lore = lore.toString(),
        tags = tags.toString(),
        title = title.toString()
    )

fun ChampionEntity.toChampionRoom() =
    ChampionRoom(
        id = id,
        blurb = blurb,
        image = image,
        name = name,
        lore = lore,
        tags = tags,
        title = title
    )

fun PassiveEntity.toPassiveRoom() =
    PassiveRoom(
        name = name,
        description = description,
        image = image
    )

fun SpellEntity.toSpellRoom() =
    SpellRoom(
        name = name,
        description = description,
        id = id,
    )

fun Spell.toSpellEntity(id: String) =
    SpellEntity(
        name = name.toString(),
        description = description.toString(),
        championid = id,
        id = this.id.toString()
    )
