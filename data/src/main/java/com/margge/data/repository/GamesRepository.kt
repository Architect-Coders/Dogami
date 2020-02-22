package com.margge.data.repository

import com.margge.data.source.LocalDataSource
import com.margge.data.source.RemoteDataSource
import com.margge.domain.Game

class GamesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val locationRepository: LocationRepository
) {

    suspend fun getBoardGames(): List<Game> {

       // if (localDataSource.isEmpty()) {
            val games = remoteDataSource.getBoardGames(locationRepository.getLastLocation())
            localDataSource.saveBoardGames(games)
        //}

        return localDataSource.getBoardGames()
    }

    suspend fun getGameById(gameId: Int) = localDataSource.getBoardGameById(gameId)

    suspend fun updateGame(game: Game) = localDataSource.updateGame(game)
}