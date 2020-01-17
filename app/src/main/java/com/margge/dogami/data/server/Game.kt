package com.margge.dogami.data.server

data class Game(
    val id: Int,
    val name: String,
    val minPlayers: Int,
    val maxPlayers: Int,
    val description: String,
    val time: String,
    val complexity: String,
    val minAge: String,
    val imageUrl: String,
    val posterUrl: String
)