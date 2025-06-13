package com.tulgot.lol.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passive_table")
data class PassiveEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "championid")
    val championid: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "image")
    val image: String
)
