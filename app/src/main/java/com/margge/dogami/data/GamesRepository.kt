package com.margge.dogami.data

class GamesRepository {

    suspend fun getGamesList() =
        NetworkHelper.dogamiApi().getListGamesAsync().await()
}