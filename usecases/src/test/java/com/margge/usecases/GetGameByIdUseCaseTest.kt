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
class GetGameByIdUseCaseTest {

    private lateinit var useCase: GetGameByIdUseCase
    @Mock
    lateinit var gamesRepository: GamesRepository

    @Before
    fun setUp() {
        useCase = GetGameByIdUseCase(gamesRepository)
    }

    @Test
    fun `when useCase es invoked then should return a game`() {
        runBlocking {
            //When
            val gameId = 6
            val gameMock = getMockedGame()
            whenever(gamesRepository.getGameById(gameId)).thenReturn(gameMock)
            val gameResult = useCase.invoke(gameId)

            //Then
            verify(gamesRepository).getGameById(gameId)
            assertEquals(gameMock, gameResult)
        }
    }
}