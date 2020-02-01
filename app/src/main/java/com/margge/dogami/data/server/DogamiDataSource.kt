package com.margge.dogami.data.server

import com.margge.data.source.RemoteDataSource
import com.margge.dogami.data.toRoomGame
import com.margge.domain.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogamiDataSource : RemoteDataSource {

    override suspend fun getBoardGames(location:String): List<Game> =
        withContext(Dispatchers.IO) {
            DogamiNetworkHelper.dogamiApi()
                .getListGamesAsync(location = location)
                .await()
                .map { it.toRoomGame() }
        }
}