package com.margge.usecases

import com.margge.data.repository.GamesRepository
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UpdateGameUseCaseTest {

    private lateinit var useCase: UpdateGameUseCase
    @Mock
    lateinit var gamesRepository: GamesRepository

    @Before
    fun setUp() {
        useCase = UpdateGameUseCase(gamesRepository)
    }

    @Test
    fun `when useCase is invoked then should updateGame`() {
        runBlocking {
            //When
            val gameMock = getMockedGame()
            val gameResult = useCase.invoke(gameMock)

            //Then
            verify(gamesRepository).updateGame(gameResult)
        }
    }

    @Test
    fun `when useCase is invoke with favorite false then should return favorite true`() {
        runBlocking {
            //When
            val gameMock = getMockedGame().copy(favorite = false)
            val gameResult = useCase.invoke(gameMock)

            //Then
            assertEquals(gameMock.favorite, !gameResult.favorite)
            assertTrue(gameResult.favorite)
        }
    }

    @Test
    fun `when useCase is invoke with favorite true then should return favorite false`() {
        runBlocking {
            //When
            val gameMock = getMockedGame().copy(favorite = true)
            val gameResult = useCase.invoke(gameMock)

            //Then
            assertEquals(gameMock.favorite, !gameResult.favorite)
            assertFalse(gameResult.favorite)
        }
    }
}