package com.margge.usecases

import com.margge.data.repository.GamesRepository
import com.margge.domain.Game

class GetGamesUseCase(private val gamesRepository: GamesRepository) {

    suspend fun invoke(): List<Game> = gamesRepository.getBoardGames()
}