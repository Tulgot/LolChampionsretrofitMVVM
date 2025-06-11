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
    @ColumnInfo(name = "lore") val lore: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "tags") val tags:  String,
    @ColumnInfo(name = "title") val title: String
)