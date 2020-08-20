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
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application): GameDatabase =
        Room.databaseBuilder(
            app,
            GameDatabase::class.java, "boardGame-db"
        ).build()

    @Provides
    fun localDataSourceProvider(db: GameDatabase): LocalDataSource = RoomDataSource(db)

    @Provides
    fun remoteDataSourceProvider(@Named("baseUrl") baseUrl: String): RemoteDataSource =
        DogamiDataSource(DogamiNetworkHelper(baseUrl))

    @Provides
    fun permissionHelperProvider(app: Application): PermissionHelper = AndroidPermissionHelper(app)

    @Provides
    fun locationDataSourceProvider(app: Application): LocationDataSource =
        GMSLocationDataSource(app)

    @Provides
    fun coroutineDispatcherProvider(): CoroutineDispatcher = Dispatchers.Main
}