package com.margge.dogami.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Game")
data class Game(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val minPlayers: Int,
    val maxPlayers: Int,
    val description: String,
    val time: String,
    val complexity: String,
    val minAge: String,
    val imageUrl: String?,
    val posterUrl: String?,
    val favorite: Boolean
)