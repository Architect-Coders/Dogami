package com.margge.usecases

import com.margge.data.repository.GamesRepository
import com.margge.domain.Game

class GetGameByIdUseCase(private val repository: GamesRepository) {

    suspend fun invoke(gameId: Int): Game = repository.getGameById(gameId)
}