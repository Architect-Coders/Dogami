package com.margge.data.source

import com.margge.domain.Game

interface RemoteDataSource {
    suspend fun getBoardGames(location: String): List<Game>
}