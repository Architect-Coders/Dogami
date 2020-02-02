package com.margge.usecases

import com.margge.data.repository.GamesRepository
import com.margge.domain.Game

class UpdateGameUseCase(private val repository: GamesRepository) {

    suspend fun invoke(game: Game): Game =
        with(game) {
            game.copy(favorite = !favorite).also {
                repository.updateGame(it)
            }
        }
}