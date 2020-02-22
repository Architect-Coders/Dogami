package com.margge.dogami.data.server

import com.margge.data.source.RemoteDataSource
import com.margge.dogami.data.toRoomGame
import com.margge.domain.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogamiDataSource(private val networkHelper: DogamiNetworkHelper) : RemoteDataSource {

    override suspend fun getBoardGames(location: String): List<Game> =
        withContext(Dispatchers.IO) {
            networkHelper.dogamiApi()
                .getListGamesAsync(location = location)
                .await()
                .map { it.toRoomGame() }
        }
}