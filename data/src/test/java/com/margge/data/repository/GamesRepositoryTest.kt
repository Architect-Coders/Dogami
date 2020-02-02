package com.margge.data.repository

import com.margge.data.source.LocalDataSource
import com.margge.data.source.RemoteDataSource
import com.margge.domain.Game
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
class GamesRepositoryTest {

    private lateinit var gamesRepository: GamesRepository
    @Mock
    lateinit var localDataSource: LocalDataSource
    @Mock
    lateinit var remoteDataSource: RemoteDataSource
    @Mock
    lateinit var locationRepository: LocationRepository

    @Before
    fun setUp() {
        gamesRepository = GamesRepository(localDataSource, remoteDataSource, locationRepository)
    }

    @Test
    fun `given a localDataSource empty when getBoardGames is called should return a list of games`() {
        runBlocking {
            //Given
            val gamesList = listOf(getMockedGame())
            whenever(localDataSource.isEmpty()).thenReturn(false)
            whenever(localDataSource.getBoardGames()).thenReturn(gamesList)

            //When
            val listGamesResult = gamesRepository.getBoardGames()

            //Then
            assertEquals(gamesList, listGamesResult)
        }
    }

    @Test
    fun `given a localDataSource empty when getBoardGames is called should call saveBoardGames`() {
        runBlocking {
            //Given
            val gamesList = listOf(getMockedGame())
            whenever(localDataSource.isEmpty()).thenReturn(true)
            whenever(remoteDataSource.getBoardGames("CL")).thenReturn(gamesList)
            whenever(locationRepository.getLastLocation()).thenReturn("CL")

            //When
            gamesRepository.getBoardGames()

            //Then
            verify(localDataSource).saveBoardGames(gamesList)
        }
    }

    @Test
    fun `given a game id when getGameById is called should return a game with the same id`() {
        runBlocking {
            //Given
            val mockedGame = getMockedGame()
            val gameId = 1
            whenever(localDataSource.getBoardGameById(gameId)).thenReturn(mockedGame)

            //When
            val gameResult = gamesRepository.getGameById(gameId)

            //Then
            assertEquals(gameResult.id, gameId)
        }
    }

    @Test
    fun `when updateGame is called then should call update game`() {
        runBlocking {
            //When
            val mockedGame = getMockedGame()
            gamesRepository.updateGame(mockedGame)

            //Then
            verify(localDataSource).updateGame(mockedGame)
        }
    }

    private fun getMockedGame(): Game {
        return Game(
            1,
            "Hombres Lobo De Castronegro",
            8,
            18,
            "En general, existen dos bandos en la partida: aldeanos y hombres lobo",
            "10-30 minutos",
            "media",
            "10",
            "https://i.imgur.com/qQwtiPG.png",
            "https://i.imgur.com/WCQBQf7.jpg",
            false
        )
    }
}