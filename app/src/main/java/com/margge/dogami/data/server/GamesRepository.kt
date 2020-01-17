package com.margge.dogami.data.server

import com.margge.dogami.DogamiApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.margge.dogami.data.server.Game as ServerGame
import com.margge.dogami.data.database.Game as DbGame

class GamesRepository(application: DogamiApp) {

    private val db = application.db

    suspend fun getGamesList(): List<DbGame> = withContext(Dispatchers.IO) {

        with(db.gameDao()) {
            if (gameCount() <= 0) {

                val games = NetworkHelper.dogamiApi()
                    .getListGamesAsync()
                    .await()

                insertGames(games.map(ServerGame::convertToDbGame))
            }

            loadAllGames()
        }
    }

    suspend fun findById(id: Int): DbGame = withContext(Dispatchers.IO) {
        db.gameDao().findById(id)
    }

    suspend fun update(game: DbGame) = withContext(Dispatchers.IO) {
        db.gameDao().updateGame(game)
    }
}

private fun ServerGame.convertToDbGame() = DbGame(
    0,
    name,
    minPlayers,
    maxPlayers,
    description,
    time,
    complexity,
    minAge,
    imageUrl,
    posterUrl,
    false
)