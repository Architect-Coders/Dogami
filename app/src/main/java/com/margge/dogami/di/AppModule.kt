package com.margge.dogami.di

import android.app.Application
import androidx.room.Room
import com.margge.data.repository.PermissionHelper
import com.margge.data.source.LocalDataSource
import com.margge.data.source.LocationDataSource
import com.margge.data.source.RemoteDataSource
import com.margge.dogami.data.AndroidPermissionHelper
import com.margge.dogami.data.GMSLocationDataSource
import com.margge.dogami.data.database.GameDatabase
import com.margge.dogami.data.database.RoomDataSource
import com.margge.dogami.data.server.DogamiDataSource
import com.margge.dogami.data.server.DogamiNetworkHelper
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class AppModule {

    private val baseUrl = "https://api.steinhq.com/v1/storages/5df2fb845a823204986f39aa/"

    @Provides
    @Singleton
    fun databaseProvider(app: Application) =
        Room.databaseBuilder(
            app,
            GameDatabase::class.java, "boardGame-db"
        ).build()

    @Provides
    fun localDataSourceProvider(db: GameDatabase): LocalDataSource = RoomDataSource(db)

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = DogamiDataSource(DogamiNetworkHelper(baseUrl))

    @Provides
    fun permissionHelperProvider(app: Application): PermissionHelper = AndroidPermissionHelper(app)

    @Provides
    fun locationDataSourceProvider(app: Application): LocationDataSource =
        GMSLocationDataSource(app)

    @Provides
    fun coroutineDispatcherProvider(): CoroutineDispatcher = Dispatchers.Main
}