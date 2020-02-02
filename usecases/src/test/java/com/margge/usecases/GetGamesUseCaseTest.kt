package com.margge.usecases

import com.margge.data.repository.GamesRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetGamesUseCaseTest {

    private lateinit var useCase: GetGamesUseCase
    @Mock
    lateinit var gamesRepository: GamesRepository

    @Before
    fun setUp() {
        useCase = GetGamesUseCase(gamesRepository)
    }

    @Test
    fun `when useCase is invoked then should return a list of games`() {
        runBlocking {
            val gamesList = listOf(getMockedGame())
            whenever(gamesRepository.getBoardGames()).thenReturn(gamesList)

            //When
            val gamesListResult = useCase.invoke()

            //Then
            verify(gamesRepository).getBoardGames()
            assertEquals(gamesList, gamesListResult)
        }
    }
}