package com.margge.dogami.data.database

import com.margge.data.source.LocalDataSource
import com.margge.dogami.data.toDomainGame
import com.margge.dogami.data.toRoomGame
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.margge.domain.Game as DomainGame

class RoomDataSource(db: GameDatabase) : LocalDataSource {

    private val gameDao = db.gameDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { gameDao.gameCount() <= 0 }

    override suspend fun saveBoardGames(games: List<DomainGame>) {
        withContext(Dispatchers.IO) { gameDao.insertGames(games.map { it.toRoomGame() }) }
    }

    override suspend fun getBoardGames(): List<DomainGame> =
        withContext(Dispatchers.IO) { gameDao.loadAllGames().map { it.toDomainGame() } }

    override suspend fun getBoardGameById(gameId: Int): DomainGame =
        withContext(Dispatchers.IO) { gameDao.findById(gameId).toDomainGame() }

    override suspend fun updateGame(game: DomainGame) =
        withContext(Dispatchers.IO) { gameDao.updateGame(game.toRoomGame()) }
}