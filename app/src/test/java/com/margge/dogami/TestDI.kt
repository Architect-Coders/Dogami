package com.margge.dogami

import com.margge.data.repository.PermissionHelper
import com.margge.data.source.LocalDataSource
import com.margge.data.source.LocationDataSource
import com.margge.data.source.RemoteDataSource
import com.margge.dogami.di.DataModule
import com.margge.dogami.di.DogamiComponent
import com.margge.dogami.presentation.getFakeGame
import com.margge.domain.Game
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Singleton
@Component(modules = [TestAppModule::class, DataModule::class])
interface TestDogamiComponent : DogamiComponent {

    val localDataSource: LocalDataSource
    val remoteDataSource: RemoteDataSource

    @Component.Factory
    interface Factory {
        fun create(): TestDogamiComponent
    }
}

@Module
class TestAppModule {

    @Singleton
    @Provides
    fun localDataSourceProvider(): LocalDataSource = FakeLocalDataSource()

    @Singleton
    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = FakeRemoteDataSource()

    @Singleton
    @Provides
    fun permissionHelperProvider(): PermissionHelper = FakeAndroidPermissionHelper()

    @Singleton
    @Provides
    fun locationDataSourceProvider(): LocationDataSource = FakeLocationDataSource()

    @Singleton
    @Provides
    fun coroutineDispatcherProvider(): CoroutineDispatcher = Dispatchers.Unconfined
}

class FakeLocationDataSource : LocationDataSource {
    var location = "CL"
    override suspend fun getLastLocation(): String? = location
}

class FakeAndroidPermissionHelper : PermissionHelper {
    var permissionGranted = true
    override suspend fun checkPermission(): Boolean = permissionGranted
}

class FakeRemoteDataSource : RemoteDataSource {
    private var games = fakeGamesList
    override suspend fun getBoardGames(location: String): List<Game> = games
}

class FakeLocalDataSource : LocalDataSource {

    var gamesList = emptyList<Game>()

    override suspend fun isEmpty(): Boolean {
        return gamesList.isEmpty()
    }

    override suspend fun saveBoardGames(games: List<Game>) {
        gamesList = games
    }

    override suspend fun getBoardGames(): List<Game> {
        return gamesList
    }

    override suspend fun getBoardGameById(gameId: Int): Game {
        return gamesList.first { it.id == gameId }
    }

    override suspend fun updateGame(game: Game) {
        gamesList = gamesList.filterNot { it.id == game.id } + game
    }
}

val fakeGamesList = listOf(
    getFakeGame().copy(id = 1),
    getFakeGame().copy(id = 2),
    getFakeGame().copy(id = 3),
    getFakeGame().copy(id = 4),
    getFakeGame().copy(id = 5)
)