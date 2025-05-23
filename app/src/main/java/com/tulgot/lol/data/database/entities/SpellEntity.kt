package com.tulgot.lol.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spell_table")
data class SpellEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "championid")
    val championid: String
)
