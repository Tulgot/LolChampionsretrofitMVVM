package com.tulgot.lol.data

import com.tulgot.lol.data.response.ChampionDto
import com.tulgot.lol.domain.model.ChampionResponse
import com.tulgot.lol.data.response.ChampionResponseDto
import com.tulgot.lol.data.response.ImageDto
import com.tulgot.lol.data.response.PassiveDto
import com.tulgot.lol.data.response.SpellDto
import com.tulgot.lol.data.response.toChampionList
import com.tulgot.lol.domain.model.Champion
import com.tulgot.lol.domain.model.Image
import com.tulgot.lol.domain.model.Passive
import com.tulgot.lol.domain.model.Spell

fun ChampionResponseDto.toChampionResponse() = ChampionResponse(
    data = champion?.toChampionList()?.map {
        it.toChampion()
    },
    format = format,
    type = type,
    version = version
)

fun ChampionDto.toChampion() = Champion(
    blurb = blurb,
    image = image?.toImage(),
    key = key,
    lore = lore,
    id = id,
    name = name,
    tags = tags,
    title = title,
    passive = passive?.toPassive(),
    spells = spells?.map {
        it.toSpell()
    }
)

fun ImageDto.toImage() = Image(
    full = full,
    group = group
)

fun PassiveDto.toPassive() = Passive(
    description = description,
    name = name,
    image = image?.toImage()
)

fun SpellDto.toSpell() = Spell(
    description = description,
    id = id,
    image = image?.toImage(),
    name = name
)