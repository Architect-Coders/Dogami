package com.margge.dogami.data

import com.margge.dogami.data.server.DogamiGameResult as ServerGame
import com.margge.dogami.data.database.Game as RoomGame
import com.margge.domain.Game as DomainGame

fun DomainGame.toRoomGame() = com.margge.dogami.data.database.Game(
    id,
    name,
    minPlayers,
    maxPlayers,
    description,
    time,
    complexity,
    minAge,
    imageUrl,
    posterUrl,
    favorite
)

fun RoomGame.toDomainGame() = com.margge.domain.Game(
    id,
    name,
    minPlayers,
    maxPlayers,
    description,
    time,
    complexity,
    minAge,
    imageUrl,
    posterUrl,
    favorite
)

fun ServerGame.toRoomGame() = com.margge.domain.Game(
    id,
    name,
    minPlayers,
    maxPlayers,
    description,
    time,
    complexity,
    minAge,
    imageUrl,
    posterUrl,
    favorite
)