package com.margge.data.source

import com.margge.domain.Game

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveBoardGames(games: List<Game>)
    suspend fun getBoardGames(): List<Game>
    suspend fun getBoardGameById(gameId: Int): Game
    suspend fun updateGame(game: Game)
}