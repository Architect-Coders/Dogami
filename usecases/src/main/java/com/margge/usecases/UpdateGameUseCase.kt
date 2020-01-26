package com.margge.usecases

import com.margge.data.repository.GamesRepository
import com.margge.domain.Game

class UpdateGameUseCase(private val repository: GamesRepository) {

    suspend fun invoke(game: Game) = repository.updateGame(game)
}