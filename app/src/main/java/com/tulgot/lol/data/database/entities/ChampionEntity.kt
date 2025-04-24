package com.tulgot.lol.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "champion_table")
data class ChampionEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "blurb") val blurb: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "key") val key: String,
    @ColumnInfo(name = "lore") val lore: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "passive") val passive: String,
    @ColumnInfo(name = "spells") val spells: String,
    @ColumnInfo(name = "tags") val tags:  String,
    @ColumnInfo(name = "title") val title: String
)

//val numbers = listOf(1, 2, 3, 4, 5, 6)
//val snumbers = numbers.toString()
//val lnumbers = "[1, 2, 3, 4, 5, 6]"
//val ynumbers = lnumbers.substring(1, lnumbers.length-1)
//val xnumbers = ynumbers.split(",").map { it.trim() }
//val numberlist = xnumbers.toList()
//println(numberlist[2])